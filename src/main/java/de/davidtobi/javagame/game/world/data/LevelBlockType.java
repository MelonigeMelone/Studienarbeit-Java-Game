package de.davidtobi.javagame.game.world.data;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.resource.model.SpriteSheet;

public enum LevelBlockType {
    BUILDING("/img/world/buildings.png"),
    PROP("/img/world/props.png"),
    TILESET("/img/world/tileset.png"),;

    private final SpriteSheet spriteSheet;

    LevelBlockType(String resourcePath) {
        this.spriteSheet = GameEngine.getResourceController().loadResource(resourcePath, SpriteSheet.class);
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
}
