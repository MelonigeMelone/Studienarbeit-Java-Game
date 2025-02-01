package de.davidtobi.javagame.game.world;

public enum LevelBlockType {
    TREE("/img/tree.png"),
    GRASS("/img/grass.png"),
    ROCK("/img/stone.png"),
    GATE("/img/gate.png");

    private String resourcePath;

    LevelBlockType(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
