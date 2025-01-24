package de.davidtobi.javagame.engine.ecs.system;

import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.model.System;
import de.davidtobi.javagame.engine.event.EventHandler;
import de.davidtobi.javagame.engine.event.event.EntityMoveEvent;
import de.davidtobi.javagame.engine.input.InputHandler;

public class MovementSystem extends System {

    @Override
    public Class<? extends Component>[] getRequiredComponents() {
        return new Class[]{PositionComponent.class, VelocityComponent.class};
    }

    @Override
    public void update(float deltaTime, int gameSpeed, boolean isPaused, EventHandler eventHandler, InputHandler inputHandler) {
        if(isPaused) {
            return;
        }

        for (Entity entity : getEnabledEntities()) {
            PositionComponent position = entity.getComponent(PositionComponent.class);
            VelocityComponent velocity = entity.getComponent(VelocityComponent.class);

            if (position != null && velocity != null) {

                EntityMoveEvent entityMoveEvent = new EntityMoveEvent(entity, position, velocity);
                eventHandler.callEvent(entityMoveEvent);

                if(!entityMoveEvent.isCancelled()) {
                    float newX = position.getX() + (velocity.getVx() * deltaTime * gameSpeed);
                    float newY = position.getY() + (velocity.getVy() * deltaTime * gameSpeed);
                    float newZ = position.getZ() + (velocity.getVz() * deltaTime * gameSpeed);

                    position.setX(newX);
                    position.setY(newY);
                    position.setZ(newZ);
                }
            }
        }
    }

    @Override
    public int getSystemPriority() {
        return 90;
    }
}
