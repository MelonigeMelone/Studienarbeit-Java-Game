package de.davidtobi.javagame.engine;

import de.davidtobi.javagame.engine.core.GameSettings;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;
import de.davidtobi.javagame.engine.scene.Scene;

public abstract class AbstractGame {

    public abstract EngineLoggerLevel getLoggerLevel();

    public abstract String getName();

    public abstract GameSettings getGameSettings();

    public abstract Scene getStartScene();
}
