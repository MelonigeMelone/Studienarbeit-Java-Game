package de.davidtobi.javagame.game.world;

public enum WorldBlock {
    TREE("/img/tree.png"),
    GRASS("/img/grass.png");

    private String resourcePath;

    WorldBlock(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
