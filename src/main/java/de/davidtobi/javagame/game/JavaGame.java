package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.AbstractGame;
import de.davidtobi.javagame.engine.core.GameSettings;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.game.scene.LevelScene;
import de.davidtobi.javagame.game.world.Level;
import de.davidtobi.javagame.game.world.LevelBlock;
import de.davidtobi.javagame.game.world.LevelBlockType;

import java.util.ArrayList;

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
        return new LevelScene(new Level(100, 50, 50, 25, 25,
                LevelBlockType.GRASS, new ArrayList<>() {{
                    add(new LevelBlock(23,23, LevelBlockType.TREE, true));
        }}));
    }
}
