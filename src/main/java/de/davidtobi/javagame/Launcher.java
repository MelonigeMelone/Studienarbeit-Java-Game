package de.davidtobi.javagame;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.game.JavaGame;

public class Launcher {

    public static void main(String[] args) {
        GameEngine gameEngine = GameEngine.getInstance();
        gameEngine.start(new JavaGame());
    }
}
