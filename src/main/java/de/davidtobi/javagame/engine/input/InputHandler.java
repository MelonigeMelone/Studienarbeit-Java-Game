package de.davidtobi.javagame.engine.input;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.event.EventHandler;
import de.davidtobi.javagame.engine.event.event.input.KeyPressedEvent;
import de.davidtobi.javagame.engine.event.event.input.KeyReleasedEvent;
import de.davidtobi.javagame.engine.event.event.input.MouseReleasedEvent;
import de.davidtobi.javagame.engine.math.model.Vector2D;

import javax.swing.*;
import java.awt.event.*;

public class InputHandler extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

    private final boolean[] keys = new boolean[256];
    private final Vector2D mousePosition = new Vector2D(0, 0);
    private boolean mousePressed = false;

    private final float cursorOffsetX = 8;
    private final float cursorOffsetY = 8;

    private final EventHandler eventHandler;

    public InputHandler() {
        this.eventHandler = GameEngine.getEventHandler();

        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        keys[keyCode] = true;
        eventHandler.callEvent(new KeyPressedEvent(keyCode));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        keys[keyCode] = false;
        eventHandler.callEvent(new KeyReleasedEvent(keyCode));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;

        eventHandler.callEvent(new MouseReleasedEvent(mousePosition));
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition.setX(e.getX()- cursorOffsetX);
        mousePosition.setY(e.getY() - cursorOffsetY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.setX(e.getX() - cursorOffsetX);
        mousePosition.setY(e.getY() - cursorOffsetY);
    }

    public boolean isKeyPressed(int keyCode) {
        return keys[keyCode];
    }

    public Vector2D getMousePos() {
        return mousePosition;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }
}
