package de.davidtobi.javagame.engine.ecs.system;

import de.davidtobi.javagame.engine.ecs.component.FollowEntityComponent;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.model.System;
import de.davidtobi.javagame.engine.event.EventHandler;
import de.davidtobi.javagame.engine.input.InputHandler;
import de.davidtobi.javagame.engine.math.model.Vector2D;

public class FollowEntitySystem extends System {

    @Override
    public Class<? extends Component>[] getRequiredComponents() {
        return new Class[] {PositionComponent.class, FollowEntityComponent.class};
    }

    @Override
    public int getSystemPriority() {
        return 10;
    }

    @Override
    public void update(float deltaTime, int gameSpeed, boolean isGamePaused, EventHandler eventHandler, InputHandler inputHandler) {
        if(isGamePaused) {
            return;
        }

        for (Entity entity : getEnabledEntities()) {
            FollowEntityComponent followEntityComponent = entity.getComponent(FollowEntityComponent.class);
            Entity target = followEntityComponent.getTarget();
            Vector2D offset = followEntityComponent.getOffset();

            if(!target.hasComponent(PositionComponent.class)) {
                return;
            }

            PositionComponent taregtPositionComponent = target.getComponent(PositionComponent.class);
            PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
            
            positionComponent.setX(taregtPositionComponent.getX() + offset.getX());
            positionComponent.setY(taregtPositionComponent.getY() + offset.getY());
        }
    }
}
