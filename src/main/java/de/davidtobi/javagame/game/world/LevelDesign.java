package de.davidtobi.javagame.game.world;

import java.util.List;

public class LevelDesign {

    private final LevelBlockType floor;
    private final List<LevelBlock> decorations;

    public LevelDesign(LevelBlockType floor, List<LevelBlock> decorations) {
        this.floor = floor;
        this.decorations = decorations;
    }

    public LevelBlockType getFloor() {
        return floor;
    }

    public List<LevelBlock> getDecorations() {
        return decorations;
    }
}
