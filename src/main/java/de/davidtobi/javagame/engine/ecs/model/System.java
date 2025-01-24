package de.davidtobi.javagame.engine.ecs.model;

import de.davidtobi.javagame.engine.event.EventHandler;
import de.davidtobi.javagame.engine.input.InputHandler;

public abstract class System extends BaseSystem {

    public abstract void update(float deltaTime, int gameSpeed, boolean isGamePaused, EventHandler eventHandler, InputHandler inputHandler);
}
