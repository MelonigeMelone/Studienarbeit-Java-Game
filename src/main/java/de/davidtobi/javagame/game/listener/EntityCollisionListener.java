package de.davidtobi.javagame.game.listener;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.ecs.component.CollidableComponent;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.SizeComponent;
import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.event.event.EntityMoveEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.engine.math.model.Vector3D;

public class EntityCollisionListener implements Listener {

    @EventHandler
    public void onEntityMove(EntityMoveEvent entityMoveEvent) {
        Entity entity = entityMoveEvent.getEntity();
        if(!entity.hasComponent(CollidableComponent.class)) {
            return;
        }


        boolean collision = GameEngine.getEntityController().getEntities().stream().filter(target ->
                !target.isDisabled() && !target.getUUID().equals(entity.getUUID()) &&
                        target.hasComponent(CollidableComponent.class))
                .anyMatch(target -> collidesWithEntity(entity, target, entityMoveEvent.getNextPosition()));

        entityMoveEvent.setCancelled(collision);
    }

    public boolean collidesWithEntity(Entity entity, Entity target, Vector3D nextPosition) {
        if(!(entity.hasComponent(PositionComponent.class) && entity.hasComponent(SizeComponent.class))) {
            return false;
        }

        SizeComponent sizeComponent = entity.getComponent(SizeComponent.class);

        PositionComponent targetPositionComponent = target.getComponent(PositionComponent.class);
        SizeComponent targetSizeComponent = target.getComponent(SizeComponent.class);

        return nextPosition.getX() < targetPositionComponent.getX() + targetSizeComponent.getWidth() &&
                nextPosition.getX()  + sizeComponent.getWidth() > targetPositionComponent.getX() &&
                nextPosition.getY() < targetPositionComponent.getY() + targetSizeComponent.getHeight() &&
                nextPosition.getY() + sizeComponent.getHeight() > targetPositionComponent.getY();
    }
}
