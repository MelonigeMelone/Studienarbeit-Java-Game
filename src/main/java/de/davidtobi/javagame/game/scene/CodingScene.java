package de.davidtobi.javagame.game.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.SyntaxHighlighter;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.component.ui.UICodingComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UIPositionComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UISizeComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.UIEntityRendererSystem;
import de.davidtobi.javagame.engine.event.event.input.KeyReleasedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.util.JavaCompilerUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.util.List;

public class CodingScene extends Scene implements Listener {

    private String code = "public class SyntaxHighlightingExample {\n" +
            "    public static void main(String[] args) {\n" +
            "        // This is a simple example for syntax highlighting\n" +
            "        String greeting = \"Hello, World!\";  // String variable\n" +
            "        int number = 42;                    // Integer variable\n" +
            "        double pi = 3.14159;                // Double variable\n" +
            "\n" +
            "        // Example of a conditional statement\n" +
            "        if (number > 10) {\n" +
            "            System.out.println(\"The number is greater than 10.\");\n" +
            "        } else {\n" +
            "            System.out.println(\"The number is less than or equal to 10.\");\n" +
            "        }\n" +
            "\n" +
            "        // Example of a loop\n" +
            "        for (int i = 0; i < 5; i++) {\n" +
            "            System.out.println(\"Loop iteration: \" + i);\n" +
            "        }\n" +
            "\n" +
            "        // Example of a math operation\n" +
            "        double result = pi * number;\n" +
            "        System.out.println(\"The result is: \" + result);\n" +
            "\n" +
            "        // Multi-line comment example\n" +
            "        /* This is a multi-line comment.\n" +
            "           It can span across multiple lines. */\n" +
            "\n" +
            "        // Another single-line comment\n" +
            "        // The program ends here.\n" +
            "    }\n" +
            "}\n";

    public CodingScene() {
        super("scene_coding");

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        addRendererSystem(new UIEntityRendererSystem());
        addListener(this);

        addEntity(new Entity("CodingBox", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(300), dimensionHelper.getCenteredY(300), 0),
                new UISizeComponent(300,300),
                new UICodingComponent(Color.WHITE, new Font("Arial", Font.PLAIN, 12),
                        HorizontalAlignment.LEFT, VerticalAlignment.TOP, SyntaxHighlighter.JAVA, () -> code
        ))));
    }

    @Override
    public void onEnter() {

    }

    @EventHandler
    public void onKeyReleased(KeyReleasedEvent event) {
        int keyCode = event.getKeyCode();
        char keyChar = event.getKeyChar();

        if (keyCode == KeyEvent.VK_BACK_SPACE && !code.isEmpty()) {
            // Remove last character on Backspace
            code = code.substring(0, code.length() - 1);
        } else if (keyCode == KeyEvent.VK_ENTER) {
            // Add newline on Enter key
            code += "\n";
        } else if(keyCode == KeyEvent.VK_SPACE) {
            code += " ";
        } else if (Character.isLowerCase(keyChar) || Character.isDigit(keyChar) || isAllowedSymbol(keyChar)) {
            // Append only lowercase letters, numbers, and allowed symbols
            code += keyChar;
        }


        try {
            // Compile & Load class
            Class<?> clazz = JavaCompilerUtil.compileAndLoad(code, "test");

            // Call method dynamically
            Method method = clazz.getMethod("getValue");
            Object result = method.invoke(null);

            System.out.println("Captured Value: " + result);
        } catch (Exception exception) {

        }
    }

    // Helper method to check if a character is an allowed symbol
    private boolean isAllowedSymbol(char c) {
        return "!@#$%^&*()-_=+{}[];:'\",.<>?/\\|".indexOf(c) >= 0;
    }

}
