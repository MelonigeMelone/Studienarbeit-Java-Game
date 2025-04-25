package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.AbstractGame;
import de.davidtobi.javagame.engine.core.GameSettings;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.game.scene.MainMenuScene;

public class JavaGame extends AbstractGame {
    @Override
    public EngineLoggerLevel getLoggerLevel() {
        return EngineLoggerLevel.INFORMATION;
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
        return new MainMenuScene();
        //return new CodingScene(CodingTasks.TEST_FUNCTIONALITY);
        /*return new LevelScene(new Level(
                50,
                20,
                15,
                17,
                2,
                LevelBlockType.GRASS,
                new ArrayList<>() {{
                    for (int x = 0; x < 20; x++) {
                        add(new LevelBlock(x, 0, LevelBlockType.TREE, true));
                        add(new LevelBlock(x, 14, LevelBlockType.TREE, true));
                    }
                    for (int y = 1; y < 14; y++) {
                        add(new LevelBlock(0, y, LevelBlockType.TREE, true));
                        add(new LevelBlock(19, y, LevelBlockType.TREE, true));
                    }

                    add(new LevelBlock(2, 13, LevelBlockType.PATH_LEFT_TURN, false));
                    for (int x = 3; x < 7; x++) {
                        add(new LevelBlock(x, 13, LevelBlockType.PATH_HORIZONTAL, false));
                    }

                    for (int y = 2; y < 13; y++) {
                        add(new LevelBlock(2, y, LevelBlockType.PATH_STRAIGHT, false));
                    }

                    add(new LevelBlock(7, 13, LevelBlockType.PATH_RIGHT_TURN, false));
                    for(int y = 12; y > 9; y--){
                        add(new LevelBlock(7, y, LevelBlockType.PATH_STRAIGHT, false));
                    }
                    add(new LevelBlock(7, 9, LevelBlockType.PATH_LEFT_TURN_REVERSE, false));

                    for (int x = 8; x < 13; x++) {
                        add(new LevelBlock(x, 9, LevelBlockType.PATH_HORIZONTAL, false));
                    }
                    add(new LevelBlock(13, 9, LevelBlockType.PATH_RIGHT_TURN, false));

                    for(int y = 8; y > 5; y--){
                        add(new LevelBlock(13, y, LevelBlockType.PATH_STRAIGHT, false));
                    }

                    add(new LevelBlock(13, 5, LevelBlockType.PATH_RIGHT_TURN_REVERSE, false));
                    for (int x = 12; x > 5; x--) {
                        add(new LevelBlock(x, 5, LevelBlockType.PATH_HORIZONTAL, false));
                    }
                    add(new LevelBlock(6, 5, LevelBlockType.PATH_LEFT_TURN, false));

                    for(int y = 4; y > 0; y--){
                        add(new LevelBlock(6, y, LevelBlockType.PATH_STRAIGHT, false));
                    }

                    add(new LevelBlock(6, 1, LevelBlockType.PATH_LEFT_TURN_REVERSE, false));
                    for (int x = 7; x < 19; x++) {
                        add(new LevelBlock(x, 1, LevelBlockType.PATH_HORIZONTAL, false));
                    }

                    add(new LevelBlock(18, 1, LevelBlockType.PATH_RIGHT_TURN_REVERSE, false));
                    for (int y = 2; y < 7; y++) {
                        add(new LevelBlock(18, y, LevelBlockType.PATH_STRAIGHT, false));
                    }

                    for (int y = 1; y < 12; y++) {
                        add(new LevelBlock(4, y, LevelBlockType.TREE, true));
                    }
                    for (int x = 5; x < 12; x++) {
                        add(new LevelBlock(x, 7, LevelBlockType.TREE, true));
                    }
                    for (int x = 8; x < 17; x++) {
                        add(new LevelBlock(x, 3, LevelBlockType.TREE, true));
                    }
                    for (int y = 4; y < 14; y++) {
                        add(new LevelBlock(16, y, LevelBlockType.TREE, true));
                    }
                    for (int x = 9; x < 16; x++) {
                        add(new LevelBlock(x, 11, LevelBlockType.TREE, true));
                    }
                    for (int y = 12; y < 14; y++) {
                        add(new LevelBlock(9, y, LevelBlockType.TREE, true));
                    }

                    add(new LevelBlock(18, 7, LevelBlockType.GATE, true, new Supplier<Scene>() {
                        @Override
                        public Scene get() {
                            return new CodingScene(CodingTasks.GATE_1);
                        }
                    }));
                    add(new LevelBlock(19, 7, LevelBlockType.ROCK, true));

                    List<LevelBlock> temp = new ArrayList<>();

                    for(int x = 0; x<20; x++) {
                        for(int y = 0; y<15; y++) {
                            boolean blockExists = false;
                           for(LevelBlock levelBlock : this) {
                               if(levelBlock.getX() == x && levelBlock.getY() == y) {
                                   blockExists = true;
                               }
                           }

                           if(!blockExists) {
                               int random = (int) (Math.random() * 100);
                               if(random < 10) {
                                   temp.add(new LevelBlock(x, y, LevelBlockType.FLOWER_1, false));
                               } else if(random < 15) {
                                   temp.add(new LevelBlock(x, y, LevelBlockType.ROCK, false));
                               }
                           }
                        }
                    }

                    for(LevelBlock levelBlock : temp) {
                        add(levelBlock);
                    }
                }}
        ));*/
    }
}
