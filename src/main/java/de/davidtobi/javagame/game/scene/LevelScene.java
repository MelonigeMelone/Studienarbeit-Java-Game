package de.davidtobi.javagame.game.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.ShowMode;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.component.*;
import de.davidtobi.javagame.engine.ecs.component.TextComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.*;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.EntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.renderersystem.UIEntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.system.MovementSystem;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.data.TextSequences;
import de.davidtobi.javagame.game.listener.EntityCollisionListener;
import de.davidtobi.javagame.game.listener.InteractListener;
import de.davidtobi.javagame.game.listener.PlayerMovementListener;
import de.davidtobi.javagame.game.listener.TextSequenceListener;
import de.davidtobi.javagame.game.model.TextSequence;
import de.davidtobi.javagame.game.world.Level;
import de.davidtobi.javagame.game.world.LevelBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LevelScene extends Scene {

    private final Level level;

    private final Camera camera;
    private final Entity player, roboter;

    private Entity textbox;
    private Entity textboxText;
    private Entity textboxNarrator;

    private Entity collidingEntity;

    private boolean inTextSequence = false;
    private TextSequence currentTextSequence;

    public LevelScene(Level level) {
        super("scene_level");

        this.level = level;

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        camera = new Camera(dimensionHelper.getCenteredX(300), dimensionHelper.getCenteredY(300
        ), GameEngine.getDimensionHelper().getCurrentDimension().width,
                GameEngine.getDimensionHelper().getCurrentDimension().height);

        addRendererSystem(new EntityRendererSystem(camera));
        addRendererSystem(new UIEntityRendererSystem());

        addSystem(new MovementSystem());

        player = new Entity("Player", List.of(
                new PositionComponent(level.getPlayerStartX() * level.getScale(), level.getPlayerStartY() * level.getScale(), 3),
                new SizeComponent(level.getScale(), level.getScale()),
                //new TextureComponent(GameEngine.getResourceController().loadResource("/img/SpriteSheet.png", Texture.class)),
                new SpriteSheetComponent(GameEngine.getResourceController().loadResource("/img/SpriteSheet.png", Texture.class), 64, 64),
                new RotationComponent(0),
                new VelocityComponent(0,0,0),
                new CollidableComponent())
        );

        roboter = new Entity("Roboter", List.of(
                new PositionComponent(level.getPlayerStartX() * level.getScale() + 15, level.getPlayerStartY() * level.getScale() + 15, 3),
                new SizeComponent((int) (level.getScale() * 0.6), (int) (level.getScale() * 0.6)),
                new SpriteSheetComponent(GameEngine.getResourceController().loadResource("/img/roboterSpriteSheet.png", Texture.class), 32, 32),
                new RotationComponent(0),
                new VelocityComponent(0,0,0))
        );

        addEntity(roboter);
        addEntity(player);

        for(int i = 0; i<level.getSizeX(); i++) {
            for(int j = 0; j<level.getSizeY(); j++) {
                addEntity(new Entity("Level_Background_" + i + j, List.of(
                        new PositionComponent(level.getScale() + i * level.getScale(), level.getScale() + j * level.getScale(), 2),
                        new SizeComponent(level.getScale(), level.getScale()),
                        new TextureComponent(GameEngine.getResourceController().loadResource(level.getBackground().getResourcePath(), Texture.class))
                )));
            }
        }

        int decorationCounter = 0;
        for(LevelBlock levelBlock : level.getDecorations()) {
            List<Component> levelBlockComponents = new ArrayList<>(List.of(
                    new PositionComponent(level.getScale() * levelBlock.getX(), level.getScale() * levelBlock.getY(), 2),
                    new SizeComponent(level.getScale(), level.getScale()),
                    new TextureComponent(GameEngine.getResourceController().loadResource(levelBlock.getLevelBlockType().getResourcePath(), Texture.class)
                    )));

            if(levelBlock.isCollidable()) {
                levelBlockComponents.add(new CollidableComponent());
            }

            Entity block = new Entity("Level_Decoration" + decorationCounter, levelBlockComponents);
            addEntity(block);

            if(levelBlock.isInteractable()) {
                levelBlock.setEntity(block);
            }

            decorationCounter++;
        }

        textboxNarrator = new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1080) + 100, dimensionHelper.getCenteredY(608) + 450, 10),
                new UISizeComponent(1080, 608),
                new UILabelComponent("", Color.WHITE, new Font("Arial", Font.PLAIN, 30), HorizontalAlignment.CENTER, VerticalAlignment.TOP, new Supplier<String>() {
                    @Override
                    public String get() {
                        if(currentTextSequence == null) {
                            return "";
                        }
                        return currentTextSequence.getTextSequences().getNarrator();
                    }
                })
        ));
        textboxNarrator.setDisabled(true);
        addEntity(textboxNarrator);

        textboxText = new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(900), dimensionHelper.getCenteredY(608) + 510, 10),
                new UISizeComponent(900, 608),
                new UILabelComponent("", Color.WHITE, new Font("Arial", Font.PLAIN, 20), HorizontalAlignment.LEFT, VerticalAlignment.TOP, new Supplier<String>() {
                    @Override
                    public String get() {
                        if(currentTextSequence == null) {
                            return "";
                        }
                        return currentTextSequence.getCurrentSequence();
                    }
                })
        ));
        textboxText.setDisabled(true);
        addEntity(textboxText);

        textbox = new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1080), 450, 0),
                new UISizeComponent(1080, 608),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/textbox1.png", Texture.class))
        ));
        textbox.setDisabled(true);
        addEntity(textbox);



        openTextSequence(new TextSequence(TextSequences.INTRO));

        addListener(new PlayerMovementListener(camera, player, this));
        addListener(new TextSequenceListener(this));
        addListener(new EntityCollisionListener(this));
        addListener(new InteractListener(this));
    }

    @Override
    public void onEnter() {
        camera.update(player);
    }

    public boolean isInTextSequence() {
        return inTextSequence;
    }

    public TextSequence getCurrentTextSequence() {
        return currentTextSequence;
    }

    public void openTextSequence(TextSequence textSequence) {
        this.inTextSequence = true;
        this.currentTextSequence = textSequence;

        textbox.setDisabled(false);
        textboxText.setDisabled(false);
        textboxNarrator.setDisabled(false);
    }

    public void closeTextSequence() {
        this.inTextSequence = false;
        this.currentTextSequence = null;

        textbox.setDisabled(true);
        textboxText.setDisabled(true);
        textboxNarrator.setDisabled(true);
    }

    public Entity getPlayer() {
        return player;
    }

    public Entity getRoboter() {
        return roboter;
    }

    public Entity getCollidingEntity() {
        return collidingEntity;
    }

    public void setCollidingEntity(Entity collidingEntity) {
        this.collidingEntity = collidingEntity;
    }

    public Level getLevel() {
        return level;
    }
}
