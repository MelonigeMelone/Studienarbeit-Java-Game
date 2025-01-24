package de.davidtobi.javagame.engine.core;

import de.davidtobi.javagame.engine.ecs.controller.SystemController;
import de.davidtobi.javagame.engine.window.GameWindow;

public class GameLoop implements Runnable {

    private static final long MS_PER_FRAME = 16;

    private boolean running = true;

    private final SystemController systemController;
    private final GameWindow gameWindow;

    public GameLoop(SystemController systemController,GameWindow gameWindow) {
        this.systemController = systemController;
        this.gameWindow = gameWindow;
    }

    @Override
    public void run() {
        long previousTime = getCurrentTime();
        long lag = 0L;
        long currentTime, elapsedTime;

        while (running) {
            currentTime = getCurrentTime();
            elapsedTime = currentTime - previousTime;
            previousTime = currentTime;
            lag += elapsedTime;

            processInput();

            while(lag >= MS_PER_FRAME) {
                update();
                lag -= MS_PER_FRAME;
            }

            render();
        }
    }

    public void stop() {
        running = false;
    }

    private void processInput() {
    }

    private void update() {

        float deltaTime = MS_PER_FRAME / 1000.0f;
        this.systemController.updateSystems(deltaTime);
    }

    private void render() {
        gameWindow.render();
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }
}

