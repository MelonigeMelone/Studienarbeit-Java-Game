package de.davidtobi.javagame.game.world.data;
import java.awt.image.BufferedImage;

public enum LevelBlockData {
    FLOOR_1(LevelBlockType.TILESET, 0, 0, 16, 16),
    ;
    private final LevelBlockType type;
    private final int x, y;
    private final int width, height;

    LevelBlockData(LevelBlockType type, int x, int y, int width, int height) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public LevelBlockType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getSprite() {
        return type.getSpriteSheet().getSprite(x, y, width, height);
    }
}
