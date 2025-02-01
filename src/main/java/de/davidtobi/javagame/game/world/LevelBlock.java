package de.davidtobi.javagame.game.world;

public class LevelBlock {

    private final int x, y;
    private final LevelBlockType levelBlockType;
    private final boolean collidable;

    public LevelBlock(int x, int y, LevelBlockType levelBlockType, boolean collidable) {
        this.x = x;
        this.y = y;
        this.levelBlockType = levelBlockType;
        this.collidable = collidable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public LevelBlockType getLevelBlockType() {
        return levelBlockType;
    }

    public boolean isCollidable() {
        return collidable;
    }
}
