package de.davidtobi.javagame.game.world;

import java.util.List;

public enum LevelData {
    ;


    private final LevelBlockType background;
    private final List<LevelBlock> decorations;

    LevelData(LevelBlockType background, List<LevelBlock> decorations) {
        this.background = background;
        this.decorations = decorations;
    }

    public LevelBlockType getBackground() {
        return background;
    }

    public List<LevelBlock> getDecorations() {
        return decorations;
    }

}
