package de.davidtobi.javagame.game.world.data;

import de.davidtobi.javagame.game.world.Level;

import java.util.ArrayList;

public enum LevelData {
    TEST(new Level( 50,
            20,
            15,
            17,
            2,
            LevelBlockData.FLOOR_1,
            new ArrayList<>() {{}}));


    private final Level level;

    LevelData(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}
