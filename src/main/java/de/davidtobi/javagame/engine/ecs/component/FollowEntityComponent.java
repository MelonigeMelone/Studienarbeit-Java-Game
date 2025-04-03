package de.davidtobi.javagame.engine.ecs.component;

import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.math.model.Vector2D;

public class FollowEntityComponent implements Component {

    private final Entity target;
    private final Vector2D offset;

    public FollowEntityComponent(Entity target, Vector2D offset) {
        this.target = target;
        this.offset = offset;
    }

    public Entity getTarget() {
        return target;
    }

    public Vector2D getOffset() {
        return offset;
    }
}
