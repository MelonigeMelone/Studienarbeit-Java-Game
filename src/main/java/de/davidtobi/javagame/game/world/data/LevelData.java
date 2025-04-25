package de.davidtobi.javagame.game.world.data;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.game.codingtask.CodingTasks;
import de.davidtobi.javagame.game.scene.CodingScene;
import de.davidtobi.javagame.game.scene.LevelScene;
import de.davidtobi.javagame.game.textsequence.data.TextSequences;
import de.davidtobi.javagame.game.world.Level;
import de.davidtobi.javagame.game.world.LevelBlock;

import java.util.ArrayList;
import java.util.function.Supplier;

public enum LevelData {
    START(new Level( 2,
            20,
            15,
            25,
            25,
            LevelBlockData.FLOOR_1,
            TextSequences.INTRO,
            new ArrayList<>() {{
                add(new LevelBlock(16, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(32, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(48, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(64, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(80, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(96, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(112, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(128, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(144, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(160, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(176, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(192, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(208, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(224, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(240, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(256, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(272, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(288, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(304, 0, LevelBlockData.WALL, true));
                add(new LevelBlock(320, 0, LevelBlockData.WALL, true));



                add(new LevelBlock(330, 31, LevelBlockData.BORDER_SIDE, true));
                add(new LevelBlock(330, 79, LevelBlockData.BORDER_SIDE, true));
                add(new LevelBlock(330, 127, LevelBlockData.BORDER_SIDE, true));
                add(new LevelBlock(330, 175, LevelBlockData.BORDER_SIDE, true));
                add(new LevelBlock(330, 223, LevelBlockData.BORDER_SIDE, true));

                add(new LevelBlock(7, 31, LevelBlockData.BORDER_SIDE, true));
                add(new LevelBlock(7, 79, LevelBlockData.BORDER_SIDE, true));
                add(new LevelBlock(7, 127, LevelBlockData.BORDER_SIDE, true));
                add(new LevelBlock(7, 175, LevelBlockData.BORDER_SIDE, true));
                add(new LevelBlock(7, 223, LevelBlockData.BORDER_SIDE, true));

                add(new LevelBlock(305, 264, LevelBlockData.BUILDING_WALL, true));
                add(new LevelBlock(258, 264, LevelBlockData.BUILDING_WALL, true));
                add(new LevelBlock(210, 264, LevelBlockData.BUILDING_WALL, true));

                add(new LevelBlock(165, 260, LevelBlockData.STAIRS_FRONT, false));

                add(new LevelBlock(118, 264, LevelBlockData.BUILDING_WALL, true));
                add(new LevelBlock(80, 264, LevelBlockData.BUILDING_WALL, true));
                add(new LevelBlock(32, 264, LevelBlockData.BUILDING_WALL, true));

                add(new LevelBlock(35, 250, LevelBlockData.COMPUTER, true,
                        () -> new CodingScene(CodingTasks.HELLO_WORLD, GameEngine.getSceneController().getCurrentScene())));
            }}));


    private final Level level;

    LevelData(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}
