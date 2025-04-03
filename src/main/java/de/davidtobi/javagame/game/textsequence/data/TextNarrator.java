package de.davidtobi.javagame.game.textsequence.data;

public enum TextNarrator {
    NARRATOR("Erzähler", null),
    ROBOTER_FRIEND("Freundlicher Roboter", "/img/roboterNarrator.png")
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
