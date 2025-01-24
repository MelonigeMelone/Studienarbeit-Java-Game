package de.davidtobi.javagame.engine.event.event.input;

import de.davidtobi.javagame.engine.event.model.Event;
import de.davidtobi.javagame.engine.math.model.Vector2D;

public class MouseReleasedEvent extends Event {

    private final Vector2D position;

    public MouseReleasedEvent(Vector2D position) {
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }
}
