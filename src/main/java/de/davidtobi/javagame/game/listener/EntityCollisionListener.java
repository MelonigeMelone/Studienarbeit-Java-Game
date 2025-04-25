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
import de.davidtobi.javagame.game.scene.LevelScene;

import java.util.Optional;

public class EntityCollisionListener implements Listener {

    private final LevelScene levelScene;

    public EntityCollisionListener(LevelScene levelScene) {
        this.levelScene = levelScene;
    }

    @EventHandler
    public void onEntityMove(EntityMoveEvent entityMoveEvent) {
        Entity entity = entityMoveEvent.getEntity();
        if(!entity.hasComponent(CollidableComponent.class)) {
            return;
        }


        Optional<Entity> optionalEntity = GameEngine.getEntityController().getEntities().stream().filter(target ->
                !target.isDisabled() && !target.getUUID().equals(entity.getUUID()) &&
                        target.hasComponent(CollidableComponent.class) &&
                        collidesWithEntity(entity, target, entityMoveEvent.getNextPosition())).findFirst();

        levelScene.setCollidingEntity(optionalEntity.orElse(null));

        entityMoveEvent.setCancelled(optionalEntity.isPresent());
    }

    public boolean collidesWithEntityOld(Entity entity, Entity target, Vector3D nextPosition) {
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

    public boolean collidesWithEntity(Entity entity, Entity target, Vector3D nextPosition) {
        // Validate that both entities have the required components
        if (!(entity.hasComponent(PositionComponent.class) && entity.hasComponent(SizeComponent.class)) ||
                !(target.hasComponent(PositionComponent.class) && target.hasComponent(SizeComponent.class))) {
            return false;
        }

        // Get the size and position of the moving entity
        SizeComponent entitySize = entity.getComponent(SizeComponent.class);
        double entityNextX = nextPosition.getX();
        double entityNextY = nextPosition.getY();
        double entityWidth = entitySize.getWidth();
        double entityHeight = entitySize.getHeight();

        // Get the size and position of the target entity
        PositionComponent targetPosition = target.getComponent(PositionComponent.class);
        SizeComponent targetSize = target.getComponent(SizeComponent.class);
        double targetX = targetPosition.getX();
        double targetY = targetPosition.getY();
        double targetWidth = targetSize.getWidth();
        double targetHeight = targetSize.getHeight();

        // Adjust for potential offsets (e.g., tile size or sprite alignment)
        double entityRight = entityNextX + entityWidth;
        double entityBottom = entityNextY + entityHeight;
        double targetRight = targetX + targetWidth;
        double targetBottom = targetY + targetHeight;

        // Check for overlap using AABB collision detection
        return entityNextX < targetRight &&
                entityRight > targetX &&
                entityNextY < targetBottom &&
                entityBottom > targetY;
    }
}
