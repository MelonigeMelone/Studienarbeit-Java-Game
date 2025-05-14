package de.davidtobi.javagame.game.world.data;
import java.awt.image.BufferedImage;

public enum LevelBlockData {
    FLOOR_1(LevelBlockType.TILESET, 0, 160, 16, 16),
    FLOOR_2(LevelBlockType.TILESET, 0, 176, 16, 16),
    FLOOR_3(LevelBlockType.TILESET, 16, 176, 16, 16),
    FLOOR_CRACKED(LevelBlockType.TILESET, 16, 160, 16, 16),
    FLOOR_VENT(LevelBlockType.TILESET, 32, 176, 16, 16),

    WALL(LevelBlockType.TILESET, 48, 160, 16, 16),
    WALL_2(LevelBlockType.TILESET, 144, 0, 16, 16),

    STAIRS_FRONT(LevelBlockType.TILESET, 128, 144, 48, 48),

    BORDER_SIDE(LevelBlockType.TILESET, 160, 48, 4,48),


    BUILDING_WALL(LevelBlockType.BUILDING, 96, 48, 48, 32),

    COMPUTER(LevelBlockType.PROP, 128, 0, 16, 48),

    DECO_1(LevelBlockType.PROP, 112, 0, 16, 48),
    DECO_2(LevelBlockType.PROP, 0, 80, 16, 16),
    DECO_3(LevelBlockType.PROP, 0, 64, 48, 32),
    DECO_4(LevelBlockType.PROP, 140, 80, 48, 16),
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
