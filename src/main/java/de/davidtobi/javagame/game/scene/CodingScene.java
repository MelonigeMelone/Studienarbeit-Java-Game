package de.davidtobi.javagame.game.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.SyntaxHighlighter;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.component.ui.*;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.UIEntityRendererSystem;
import de.davidtobi.javagame.engine.event.event.input.KeyReleasedEvent;
import de.davidtobi.javagame.engine.event.event.input.MouseReleasedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.textsequence.data.TextSequences;
import de.davidtobi.javagame.game.textsequence.model.TextSequence;
import de.davidtobi.javagame.game.util.JavaCompilerUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;


public class CodingScene extends BaseGameScene implements Listener {

    private int cursorIndex;
    private boolean cursorVisible;
    private Timer cursorTimer;

    private final Entity codingBox;

    private String code = "public class Gate {\n" +
            "    public static void main(String[] args) {\n" +
            "        boolean isGateOpen = false;\n" +
            "        setGateStatus(isGateOpen);\n" +
            "    }\n" +
            "}\n";

    public CodingScene() {
        super("scene_coding");

        initCursor();

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        addRendererSystem(new UIEntityRendererSystem());
        addListener(this);

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1080), dimensionHelper.getCenteredY(1920), 0),
                new UISizeComponent(1080, 1920),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/codingBackground.png", Texture.class))
        )));

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1080), dimensionHelper.getCenteredY(608), 1),
                new UISizeComponent(1080, 608),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/textbox1.png", Texture.class))
        )));

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(900) + 150, dimensionHelper.getCenteredY(608) + 50, 10),
                new UISizeComponent(900, 608),
                new UILabelComponent("", Color.WHITE, new Font("Arial", Font.PLAIN, 30), HorizontalAlignment.CENTER, VerticalAlignment.TOP, new Supplier<String>() {
                    @Override
                    public String get() {
                        return "Gate.java";
                    }
                })
        )));

        addEntity(new Entity("CompileButton", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(108) + 325, dimensionHelper.getCenteredY(60) + 225, 2),
                new UISizeComponent(190, 45),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/buttonGreen.png", Texture.class)),
                new UILabelComponent("", Color.WHITE, new Font("Arial", Font.PLAIN, 30), HorizontalAlignment.CENTER, VerticalAlignment.TOP, new Supplier<String>() {
                    @Override
                    public String get() {
                        return "Compile";
                    }
                })
        )));

        addEntity(new Entity("HelpButton", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(108) + 425, dimensionHelper.getCenteredY(60) - 175, 2),
                new UISizeComponent(50, 50),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/helpIcon.png", Texture.class))
        )));

        codingBox = new Entity("CodingBox", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1080) + 100, dimensionHelper.getCenteredY(608) + 125, 2),
                new UISizeComponent(1080, 608),
                new UICodingComponent(Color.BLACK, new Font("Arial", Font.PLAIN, 18),
                        HorizontalAlignment.LEFT, VerticalAlignment.TOP, SyntaxHighlighter.JAVA, this::getCodeWithCursor
                )
        ));
        addEntity(codingBox);

        cursorVisible = true;
        cursorTimer = new Timer(500, e -> {
            cursorVisible = !cursorVisible;
            // Trigger a repaint or update the UI to reflect the cursor visibility change
        });
        cursorTimer.start();

        openTextSequence(new TextSequence(TextSequences.LEVEL_1_BOOLEAN));
    }

    @Override
    public void onEnter() {

    }

    @EventHandler
    public void onKeyReleased(KeyReleasedEvent event) {
        if(inTextSequence) {
            return;
        }

        int keyCode = event.getKeyCode();
        char keyChar = event.getKeyChar();

        if (keyCode == KeyEvent.VK_BACK_SPACE && cursorIndex > 0) {
            // Remove character before the cursor
            code = code.substring(0, cursorIndex - 1) + code.substring(cursorIndex);
            cursorIndex--;
        } else if (keyCode == KeyEvent.VK_DELETE && cursorIndex < code.length()) {
            // Remove character at the cursor
            code = code.substring(0, cursorIndex) + code.substring(cursorIndex + 1);
        } else if (keyCode == KeyEvent.VK_ENTER) {
            // Add newline at the cursor
            code = code.substring(0, cursorIndex) + "\n" + code.substring(cursorIndex);
            cursorIndex++;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            // Add space at the cursor
            code = code.substring(0, cursorIndex) + " " + code.substring(cursorIndex);
            cursorIndex++;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            // Move cursor left
            cursorIndex = Math.max(0, cursorIndex - 1);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            // Move cursor right
            cursorIndex = Math.min(code.length(), cursorIndex + 1);
        } else if (Character.isLowerCase(keyChar) || Character.isDigit(keyChar) || isAllowedSymbol(keyChar)) {
            // Insert character at the cursor
            code = code.substring(0, cursorIndex) + keyChar + code.substring(cursorIndex);
            cursorIndex++;
        }

        try {
            // Compile & Load class
            Class<?> clazz = JavaCompilerUtil.compileAndLoad(code, "test");

            // Call method dynamically
            Method method = clazz.getMethod("getValue");
            Object result = method.invoke(null);

            System.out.println("Captured Value: " + result);
        } catch (Exception exception) {
            // Handle exception
        }
    }

    @EventHandler
    public void onMouseClicked(MouseReleasedEvent event) {
        updateCursorPosition(event.getPosition().getX(), event.getPosition().getY());
    }


    private void updateCursorPosition(float mouseX, float mouseY) {
        UIPositionComponent positionComponent = codingBox.getComponent(UIPositionComponent.class);
        UICodingComponent codingComponent = codingBox.getComponent(UICodingComponent.class);

        FontMetrics fontMetrics = codingComponent.getFontMetrics();
        if(fontMetrics == null) {
            return;
        }
        float x = positionComponent.getX();
        float y = positionComponent.getY();
        cursorIndex = 0;

        for (String line : code.split("\n")) {
            int lineHeight = fontMetrics.getHeight();
            if (mouseY >= y && mouseY < y + lineHeight) {
                for (int i = 0; i < line.length(); i++) {
                    int charWidth = fontMetrics.charWidth(line.charAt(i));
                    if (mouseX >= x && mouseX < x + charWidth) {
                        cursorIndex += i;
                        return;
                    }
                    x += charWidth;
                }
                cursorIndex += line.length();
                return;
            }
            y += lineHeight;
            cursorIndex += line.length() + 1; // +1 for the newline character
        }
    }

    // Helper method to check if a character is an allowed symbol
    private boolean isAllowedSymbol(char c) {
        return "!@#$%^&*()-_=+{}[];:'\",.<>?/\\|".indexOf(c) >= 0;
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
