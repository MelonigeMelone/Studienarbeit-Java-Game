package de.davidtobi.javagame.game.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.SyntaxHighlighter;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.component.ui.*;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.UIEntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.system.ui.UIClickSystem;
import de.davidtobi.javagame.engine.ecs.system.ui.UIHoverSystem;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.engine.log.EngineLogger;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.codingtask.CodingTaskClass;
import de.davidtobi.javagame.game.codingtask.CodingTaskHelpSequenceType;
import de.davidtobi.javagame.game.codingtask.CodingTasks;
import de.davidtobi.javagame.game.listener.CodingSceneKeyInputListener;
import de.davidtobi.javagame.game.listener.CodingSceneMouseInputListener;
import de.davidtobi.javagame.game.textsequence.model.TextSequence;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Supplier;


public class CodingScene extends BaseGameScene implements Listener {

    private final CodingTasks codingTasks;
    private CodingTaskClass selectedCodingTaskClass;

    private int cursorIndex;
    private boolean cursorVisible;
    private Timer cursorTimer;

    private final Entity codingBox;

    private String code;

    public CodingScene(CodingTasks codingTasks) {
        super("scene_coding");

        this.codingTasks = codingTasks;
        this.selectedCodingTaskClass = codingTasks.getCodingTask().getCodingTaskClasses()[0];
        this.code = selectedCodingTaskClass.getCurrentCode();

        initCursor();

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        addRendererSystem(new UIEntityRendererSystem());
        addSystem(new UIClickSystem());
        addSystem(new UIHoverSystem());

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1920), dimensionHelper.getCenteredY(1080), 0),
                new UISizeComponent(1920, 1080),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/codingScene/background.png", Texture.class))
        )));

        int classCounter = 0;
        for(CodingTaskClass codingTaskClass : codingTasks.getCodingTask().getCodingTaskClasses()) {

            addEntity(new Entity("UI", List.of(
                    new UIPositionComponent(dimensionHelper.getCenteredX(313) - 485 + (classCounter * 400), dimensionHelper.getCenteredY(40) - 395, 1),
                    new UISizeComponent(400, 70),
                    new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/codingScene/classTab.png", Texture.class)),
                    new UIHoverComponent(GameEngine.getResourceController().loadResource("/img/ui/codingScene/classTab_hover.png", Texture.class)),
                    new UILabelComponent("", Color.WHITE, new Font("Arial", Font.PLAIN, 30), HorizontalAlignment.CENTER, VerticalAlignment.CENTER, new Supplier<String>() {
                        @Override
                        public String get() {
                            return codingTaskClass.getClassName() + ".java";
                        }
                    }),
                    new UIClickableComponent(() -> {
                        if(inTextSequence) {
                            return;
                        }
                        this.selectedCodingTaskClass = codingTaskClass;
                        this.code = codingTaskClass.getCurrentCode();
                        initCursor();
                    })
            )));
            classCounter++;
        }


        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(50) - 550, dimensionHelper.getCenteredY(50) - 450, 1),
                new UISizeComponent(40, 40),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/codingScene/compileButton.png", Texture.class)),
                new UIHoverComponent(GameEngine.getResourceController().loadResource("/img/ui/codingScene/compileButton_hover.png", Texture.class)),
                new UIClickableComponent(() -> {
                    if(inTextSequence) {
                        return;
                    }
                    this.selectedCodingTaskClass.setCurrentCode(code);

                    boolean compiledSuccessfully = false;
                    try {
                        compiledSuccessfully = codingTasks.getCodingTask().compiledSuccessfully();
                    } catch (Exception exception) {
                        EngineLogger.log(EngineLoggerLevel.ERROR, "Error while compiling: " + exception.getMessage());
                    }

                    EngineLogger.log(EngineLoggerLevel.INFORMATION, "Compile Status " + compiledSuccessfully);
                    openTextSequence(new TextSequence(
                            compiledSuccessfully ? codingTasks.getCodingTask().getHelpTextSequences()
                                    .get(CodingTaskHelpSequenceType.TASK_FINISH) : codingTasks.getCodingTask().getHelpTextSequences()
                                    .get(CodingTaskHelpSequenceType.TASK_FAILED)
                    ));
                })
        )));

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(50) - 500, dimensionHelper.getCenteredY(50) - 450, 1),
                new UISizeComponent(40, 40),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/codingScene/resetButton.png", Texture.class)),
                new UIHoverComponent(GameEngine.getResourceController().loadResource("/img/ui/codingScene/resetButton_hover.png", Texture.class)),
                new UIClickableComponent(() -> {
                    if(inTextSequence) {
                        return;
                    }

                    this.selectedCodingTaskClass.setCurrentCode(selectedCodingTaskClass.buildClassCode());
                    this.code = selectedCodingTaskClass.getCurrentCode();
                })
        )));

        codingBox = new Entity("CodingBox", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1080) - 350, dimensionHelper.getCenteredY(608), 2),
                new UISizeComponent(1080, 608),
                new UICodingComponent(Color.BLACK, new Font("Arial", Font.PLAIN, 25),
                        HorizontalAlignment.LEFT, VerticalAlignment.TOP, SyntaxHighlighter.JAVA, this::getCodeWithCursor
                )
        ));
        addEntity(codingBox);

        cursorVisible = true;
        cursorTimer = new Timer(500, e -> {
            cursorVisible = !cursorVisible;
        });
        cursorTimer.start();

        if(codingTasks.getCodingTask().hasHelpTextSequences()) {
            openTextSequence(new TextSequence(codingTasks.getCodingTask().getHelpTextSequences()
                    .get(CodingTaskHelpSequenceType.INITIAL)));
        }

        addListener(new CodingSceneKeyInputListener(this));
        addListener(new CodingSceneMouseInputListener(this));
    }

    @Override
    public void onEnter() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCursorIndex() {
        return cursorIndex;
    }

    public void setCursorIndex(int cursorIndex) {
        this.cursorIndex = cursorIndex;
    }

    public Entity getCodingBox() {
        return codingBox;
    }

    private String getCodeWithCursor() {
        StringBuilder sb = new StringBuilder();
        sb.append(code);
        if (cursorVisible) {
            sb.insert(cursorIndex, "|");
        }
        return sb.toString();
    }

    private void initCursor() {
        cursorIndex = code.length();
    }
}
