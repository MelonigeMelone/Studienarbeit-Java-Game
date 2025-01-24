package de.davidtobi.javagame.engine.ecs.model;

import java.awt.*;

public abstract class RendererSystem extends BaseSystem {

    public abstract void render(Graphics graphics);
}
