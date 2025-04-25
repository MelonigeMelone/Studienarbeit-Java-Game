package de.davidtobi.javagame.game.textsequence.data;

public enum TextNarrator {
    NARRATOR("Erzähler", "/img/narrator/ideo.png"),
    ROBOTER_FRIEND("Ideo", "/img/narrator/ideo.png")
    ;

    private final String name;
    private final String texturePath;

    TextNarrator(String name, String texturePath) {
        this.name = name;
        this.texturePath = texturePath;
    }

    public String getName() {
        return name;
    }

    public boolean hasTexture() {
        return this.texturePath != null;
    }

    public String getTexturePath() {
        return texturePath;
    }
}
