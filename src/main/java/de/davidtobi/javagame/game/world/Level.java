package de.davidtobi.javagame.game.world;

import de.davidtobi.javagame.game.world.data.LevelBlockData;

import java.util.List;

public class Level {

    private int scale;

    private int sizeX, sizeY;

    private int playerStartX, playerStartY;

    private LevelBlockData background;

    private List<LevelBlock> decorations;

    public Level(int scale, int sizeX, int sizeY, int playerStartX, int playerStartY, LevelBlockData background, List<LevelBlock> decorations) {
        this.scale = scale;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.background = background;
        this.decorations = decorations;
    }

    public int getScale() {
        return scale;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getPlayerStartX() {
        return playerStartX;
    }

    public int getPlayerStartY() {
        return playerStartY;
    }

    public LevelBlockData getBackground() {
        return background;
    }

    public List<LevelBlock> getDecorations() {
        return decorations;
    }
}
