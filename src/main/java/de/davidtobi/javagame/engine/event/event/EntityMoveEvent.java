package de.davidtobi.javagame.engine.event.event;

import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.event.model.Cancellable;
import de.davidtobi.javagame.engine.event.model.Event;
import de.davidtobi.javagame.engine.math.model.Vector3D;

public class EntityMoveEvent extends Event implements Cancellable {

    private final Entity entity;
    private final PositionComponent positionFrom;
    private final VelocityComponent velocity;
    private final Vector3D nextPosition;
    private boolean cancelled = false;

    public EntityMoveEvent(final Entity entity, final PositionComponent positionFrom, final VelocityComponent velocity, final Vector3D nextPosition) {
        this.entity = entity;
        this.positionFrom = positionFrom;
        this.velocity = velocity;
        this.nextPosition = nextPosition;
    }

    public Entity getEntity() {
        return entity;
    }

    public PositionComponent getPositionFrom() {
        return positionFrom;
    }

    public VelocityComponent getVelocity() {
        return velocity;
    }

    public Vector3D getNextPosition() {
        return nextPosition;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;

    }
}
