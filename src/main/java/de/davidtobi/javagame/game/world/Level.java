package de.davidtobi.javagame.game.world;

import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.game.textsequence.data.TextSequences;
import de.davidtobi.javagame.game.world.data.LevelBlockData;

import java.util.List;

public class Level {

    private int scale;

    private int sizeX, sizeY;

    private int playerStartX, playerStartY;

    private LevelBlockData background;

    private List<LevelBlock> decorations;

    private TextSequences introText;

    public Level(int scale, int sizeX, int sizeY, int playerStartX, int playerStartY, LevelBlockData background, TextSequences introText, List<LevelBlock> decorations) {
        this.scale = scale;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.playerStartX = playerStartX;
        this.playerStartY = playerStartY;
        this.background = background;
        this.introText = introText;
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

    public TextSequences getIntroText() {
        return introText;
    }

    public List<LevelBlock> getDecorations() {
        return decorations;
    }
}
