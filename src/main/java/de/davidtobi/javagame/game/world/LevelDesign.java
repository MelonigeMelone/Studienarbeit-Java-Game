package de.davidtobi.javagame.game.world;

import de.davidtobi.javagame.game.world.data.OldLevelBlockType;

import java.util.List;

public class LevelDesign {

    private final OldLevelBlockType floor;
    private final List<LevelBlock> decorations;

    public LevelDesign(OldLevelBlockType floor, List<LevelBlock> decorations) {
        this.floor = floor;
        this.decorations = decorations;
    }

    public OldLevelBlockType getFloor() {
        return floor;
    }

    public List<LevelBlock> getDecorations() {
        return decorations;
    }
}
