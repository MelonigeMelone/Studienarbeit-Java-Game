package de.davidtobi.javagame.game.listener;

import de.davidtobi.javagame.engine.event.event.input.KeyPressedEvent;
import de.davidtobi.javagame.engine.event.event.input.KeyReleasedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.game.scene.CodingScene;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CodingSceneKeyInputListener implements Listener {

    private final CodingScene codingScene;
    private boolean isShiftPressed = false;

    public CodingSceneKeyInputListener(CodingScene codingScene) {
        this.codingScene = codingScene;
    }

    @EventHandler
    public void onKeyPressed(KeyPressedEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
            isShiftPressed = true;
        } else {
            isShiftPressed = false;
        }
    }

    @EventHandler
    public void onKeyReleased(KeyPressedEvent event) {
        if (codingScene.isInTextSequence()) {
            return;
        }

        int keyCode = event.getKeyCode();
        char keyChar = event.getKeyChar();

        String code = codingScene.getCode();
        int cursorIndex = codingScene.getCursorIndex();

        if (keyCode == KeyEvent.VK_SHIFT) {
            isShiftPressed = false;
        } else if (Character.isLetter(keyChar)) {
            // Insert uppercase letter if Shift is pressed
            if (isShiftPressed) {
                keyChar = Character.toUpperCase(keyChar);
            }
            code = code.substring(0, cursorIndex) + keyChar + code.substring(cursorIndex);
            cursorIndex++;
        } else if (keyCode == KeyEvent.VK_BACK_SPACE && cursorIndex > 0) {
            code = code.substring(0, cursorIndex - 1) + code.substring(cursorIndex);
            cursorIndex--;
        } else if (keyCode == KeyEvent.VK_DELETE && cursorIndex < code.length()) {
            code = code.substring(0, cursorIndex) + code.substring(cursorIndex + 1);
        } else if (keyCode == KeyEvent.VK_ENTER) {
            code = code.substring(0, cursorIndex) + "\n" + code.substring(cursorIndex);
            cursorIndex++;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            code = code.substring(0, cursorIndex) + " " + code.substring(cursorIndex);
            cursorIndex++;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            cursorIndex = Math.max(0, cursorIndex - 1);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            cursorIndex = Math.min(code.length(), cursorIndex + 1);
        } else if (keyCode == KeyEvent.VK_UP) {
            cursorIndex = moveCursorUp(code, cursorIndex);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            cursorIndex = moveCursorDown(code, cursorIndex);
        } else if (Character.isDigit(keyChar) || isAllowedSymbol(keyChar)) {
            code = code.substring(0, cursorIndex) + keyChar + code.substring(cursorIndex);
            cursorIndex++;
        }

        codingScene.setCode(code);
        codingScene.setCursorIndex(cursorIndex);
    }

    private int moveCursorUp(String code, int cursorIndex) {
        int lineStart = code.lastIndexOf('\n', cursorIndex - 1);
        if (lineStart == -1) {
            return 0;
        }
        int prevLineStart = code.lastIndexOf('\n', lineStart - 1);
        if (prevLineStart == -1) {
            prevLineStart = 0;
        } else {
            prevLineStart++;
        }
        int column = cursorIndex - lineStart - 1;
        cursorIndex = Math.min(prevLineStart + column, lineStart);

        return cursorIndex;
    }

    private int moveCursorDown(String code, int cursorIndex) {

        int lineEnd = code.indexOf('\n', cursorIndex);
        if (lineEnd == -1) {
            return code.length();
        }
        int nextLineEnd = code.indexOf('\n', lineEnd + 1);
        int nextLineStart = lineEnd + 1;

        if (nextLineEnd == -1) {
            nextLineEnd = code.length();
        }

        int column = Math.max(0, cursorIndex - nextLineStart);
        cursorIndex = Math.min(nextLineStart + column, nextLineEnd);

       return cursorIndex;
    }

    private boolean isAllowedSymbol(char c) {
        return "!@#$%^&*()-_=+{}[];:'\",.<>?/\\|".indexOf(c) >= 0;
    }
}
