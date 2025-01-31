package de.davidtobi.javagame.game.world;

public class Level {

    private int sizeX, sizeY;

    private int playerStartX, playerStartY;

    private WorldBlock background;

    private WorldBlock[][] decorations;

    public Level(int sizeX, int sizeY, int playerStartX, int playerStartY, WorldBlock background, WorldBlock[][] decorations) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.background = background;
        this.decorations = decorations;
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

    public WorldBlock getBackground() {
        return background;
    }

    public WorldBlock[][] getDecorations() {
        return decorations;
    }
}
