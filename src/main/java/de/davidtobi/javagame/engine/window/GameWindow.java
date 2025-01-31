package de.davidtobi.javagame.engine.window;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.ecs.controller.SystemController;
import de.davidtobi.javagame.engine.input.InputHandler;
import de.davidtobi.javagame.engine.util.DimensionHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameWindow extends JFrame {

    private final Canvas canvas;
    private final SystemController systemController;
    private final DimensionHelper dimensionHelper;

    public GameWindow(String title, InputHandler inputHandler, SystemController systemController, DimensionHelper dimensionHelper) {
        super(title);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        GameEngine.getDimensionHelper().setDimension(screenSize);

        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        addKeyListener(inputHandler);

        this.systemController = systemController;
        this.dimensionHelper = dimensionHelper;

        canvas = new Canvas();
        canvas.setPreferredSize(screenSize);
        canvas.setMinimumSize(screenSize);
        canvas.setMaximumSize(screenSize);
        canvas.addMouseListener(inputHandler);
        canvas.addMouseMotionListener(inputHandler);
        canvas.addKeyListener(inputHandler);

        add(canvas);
        pack();
        setVisible(true);

        inputHandler.requestFocus();

    }

    public void render() {

        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        if (bufferStrategy == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        try {
            clearScreen(graphics);
            systemController.render(graphics);
        } finally {
            graphics.dispose();
            bufferStrategy.show();
        }

        dimensionHelper.setDimension(getSize());
    }

    private void clearScreen(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
