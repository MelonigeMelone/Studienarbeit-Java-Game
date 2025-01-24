package de.davidtobi.javagame.engine;
import de.davidtobi.javagame.engine.core.GameLoop;
import de.davidtobi.javagame.engine.core.GameSettings;
import de.davidtobi.javagame.engine.ecs.controller.EntityController;
import de.davidtobi.javagame.engine.ecs.controller.SystemController;
import de.davidtobi.javagame.engine.event.EventHandler;
import de.davidtobi.javagame.engine.input.InputHandler;
import de.davidtobi.javagame.engine.log.EngineLogger;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;
import de.davidtobi.javagame.engine.resource.ResourceController;
import de.davidtobi.javagame.engine.scene.SceneController;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.engine.window.GameWindow;

import java.awt.*;

public class GameEngine {

    private static EngineLogger mEngineLogger;

    private static GameEngine instance;

    private static DimensionHelper dimensionHelper;

    private static AbstractGame game;
    private static GameSettings gameSettings;

    private static EventHandler eventHandler;
    private static ResourceController resourceController;

    private SystemController systemController;
    private static EntityController entityController;
    private static SceneController sceneController;

    private InputHandler inputHandler;
    private GameWindow gameWindow;

    private GameLoop gameLoop;
    private Thread gameLoopThread;

    private GameEngine() {}

    public void start(AbstractGame game) {
        GameEngine.game = game;
        GameEngine.gameSettings = game.getGameSettings();
        EngineLogger.setMEngineLoggerLevel(game.getLoggerLevel());

        EngineLogger.log(EngineLoggerLevel.INFORMATION, "Starting MEngine");

        initialize();
        startGameLoop();

        EngineLogger.log(EngineLoggerLevel.INFORMATION, "MEngine successfully started");


        sceneController.switchScene(game.getStartScene());
    }

    public void stop() {
        gameLoop.stop();
        resourceController.clearResources();
        System.exit(1);
    }

    private void initialize() {
        eventHandler = new EventHandler();
        inputHandler = new InputHandler();
        resourceController = new ResourceController();

        systemController = new SystemController(eventHandler, inputHandler);
        entityController = new EntityController(systemController);
        sceneController = new SceneController(eventHandler, entityController, systemController);

        Dimension defaultDimension = new Dimension(1080, 720);
        dimensionHelper = new DimensionHelper(defaultDimension);
        gameWindow = new GameWindow(game.getName(), inputHandler, systemController, dimensionHelper);
    }

    private void startGameLoop() {
        gameLoop = new GameLoop(systemController, gameWindow);
        gameLoopThread = new Thread(gameLoop);
        gameLoopThread.start();
    }



    public static GameEngine getInstance() {
        if(instance == null) {
            instance = new GameEngine();
        }

        return instance;
    }

    public static AbstractGame getGame() {
        return game;
    }

    public static GameSettings getGameSettings() {
        return gameSettings;
    }

    public static DimensionHelper getDimensionHelper() {
        return dimensionHelper;
    }

    public static EventHandler getEventHandler() {
        return eventHandler;
    }

    public static ResourceController getResourceController() {
        return resourceController;
    }

    public static SceneController getSceneController() {
        return sceneController;
    }

    public static EntityController getEntityController() {
        return entityController;
    }
}
