package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.AbstractGame;
import de.davidtobi.javagame.engine.core.GameSettings;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;
import de.davidtobi.javagame.engine.scene.Scene;

public class JavaGame extends AbstractGame {
    @Override
    public EngineLoggerLevel getLoggerLevel() {
        return EngineLoggerLevel.DEBUG;
    }

    @Override
    public String getName() {
        return "JavaGame";
    }

    @Override
    public GameSettings getGameSettings() {
        return new GameSettings();
    }

    @Override
    public Scene getStartScene() {
        return new ExampleScene();
    }
}
