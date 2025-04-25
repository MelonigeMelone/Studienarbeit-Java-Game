package de.davidtobi.javagame.game.world.data;

public enum OldLevelBlockType {
    TREE("/img/tree.png"),
    GRASS("/img/grass.png"),
    ROCK("/img/stone.png"),
    FLOWER_1("/img/flower_1.png"),
    GATE("/img/gate.png"),
    PATH_STRAIGHT("/img/grass_way_straight.png"),
    PATH_HORIZONTAL("/img/grass_way_horizontal.png"),
    PATH_LEFT_TURN("/img/grass_way_left_turn.png"),
    PATH_RIGHT_TURN("/img/grass_way_right_turn.png"),
    PATH_LEFT_TURN_REVERSE("/img/grass_way_left_turn_reversed.png"),
    PATH_RIGHT_TURN_REVERSE("/img/grass_way_right_turn_reversed.png");


    private String resourcePath;

    OldLevelBlockType(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
