package de.davidtobi.javagame.game.listener;

import de.davidtobi.javagame.engine.ecs.component.ui.UICodingComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UIPositionComponent;
import de.davidtobi.javagame.engine.event.event.input.MouseReleasedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.game.scene.CodingScene;

import java.awt.*;

public class CodingSceneMouseInputListener implements Listener {

    private final CodingScene codingScene;

    public CodingSceneMouseInputListener(CodingScene codingScene) {
        this.codingScene = codingScene;
    }

    @EventHandler
    public void onMouseClicked(MouseReleasedEvent event) {
        if(codingScene.isInTextSequence()) {
            return;
        }

        codingScene.setCursorIndex(updateCursorPosition(event.getPosition().getX(), event.getPosition().getY()));
    }


    private int updateCursorPosition(float mouseX, float mouseY) {
        double percentageX = 1.1, percentageY = 1.2;

        mouseX = (int) ((double) mouseX * percentageX);
        mouseY = (int) ((double) mouseY * percentageY);


        UIPositionComponent positionComponent = codingScene.getCodingBox().getComponent(UIPositionComponent.class);
        UICodingComponent codingComponent = codingScene.getCodingBox().getComponent(UICodingComponent.class);
        FontMetrics fontMetrics = codingComponent.getFontMetrics();
        float x = positionComponent.getX();
        float y = positionComponent.getY();

        if(fontMetrics == null) {
            return 0;
        }

        String code = codingScene.getCode();
        int cursorIndex = 0;

        for (String line : code.split("\n")) {
            int lineHeight = fontMetrics.getHeight();
            if (mouseY >= y && mouseY < y + lineHeight) {
                for (int i = 0; i < line.length(); i++) {
                    int charWidth = fontMetrics.charWidth(line.charAt(i));
                    if (mouseX >= x && mouseX < x + charWidth) {
                        cursorIndex += i;
                        return cursorIndex;
                    }
                    x += charWidth;
                }
                cursorIndex += line.length();
                return cursorIndex;
            }
            y += lineHeight;
            cursorIndex += line.length() + 1;
        }
        if(cursorIndex > code.length()) {
            cursorIndex = code.length();
        }

        return cursorIndex;
    }

    private int updateCursorPosition2(float mouseX, float mouseY) {
        UIPositionComponent positionComponent = codingScene.getCodingBox().getComponent(UIPositionComponent.class);
        UICodingComponent codingComponent = codingScene.getCodingBox().getComponent(UICodingComponent.class);
        FontMetrics fontMetrics = codingComponent.getFontMetrics();
        float x = positionComponent.getX();
        float y = positionComponent.getY();

        if (fontMetrics == null) {
            return 0;
        }

        String code = codingScene.getCode();
        int cursorIndex = 0;

        for (String line : code.split("\n")) {
            int lineHeight = fontMetrics.getHeight();

            // Check if the mouseY is within the vertical bounds of this line
            if (mouseY >= y && mouseY < y + lineHeight) {
                int lineStartX = (int) x; // Store the starting x for the current line
                // Loop through each character in the line to check if mouseX falls on it
                for (int i = 0; i < line.length(); i++) {
                    int charWidth = fontMetrics.charWidth(line.charAt(i));
                    if (mouseX >= lineStartX && mouseX < lineStartX + charWidth) {
                        cursorIndex += i;
                        return cursorIndex;
                    }
                    lineStartX += charWidth; // Move to the next character's x position
                }
                cursorIndex += line.length(); // If mouseX is after the last character in the line
                return cursorIndex;
            }
            y += lineHeight; // Move down to the next line
            cursorIndex += line.length() + 1; // Add 1 for the newline character
        }

        if (cursorIndex > code.length()) {
            cursorIndex = code.length(); // Ensure the cursor index doesn't exceed the total length of code
        }

        return cursorIndex;
    }


}
