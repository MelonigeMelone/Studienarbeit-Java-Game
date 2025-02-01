package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.ecs.component.CollidableComponent;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.SizeComponent;

import de.davidtobi.javagame.engine.ecs.component.ui.RotationComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.event.event.input.KeyPressedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;

import java.awt.event.KeyEvent;

public class TestListener implements Listener {

    private final Entity player;
    private final Camera camera;

    public TestListener(Entity player, Camera camera) {
        this.player = player;
        this.camera = camera;
    }

    @EventHandler
    public void onInput(KeyPressedEvent event) {
        PositionComponent positionComponent = player.getComponent(PositionComponent.class);
        RotationComponent rotationComponent = player.getComponent(RotationComponent.class);

        float changeX = 0;
        float changeY = 0;

        if(event.getKeyCode() == KeyEvent.VK_W) {
            positionComponent.addY(25);
            changeY = 25;
            rotationComponent.setRotation(0);
        } else if(event.getKeyCode() == KeyEvent.VK_S) {
            positionComponent.addY(-25);
            changeY = -25;
            rotationComponent.setRotation(180);
        } else if(event.getKeyCode() == KeyEvent.VK_A) {
            positionComponent.addX(25);
            changeX = 25;
            rotationComponent.setRotation(270);
        } else if(event.getKeyCode() == KeyEvent.VK_D) {
            positionComponent.addX(-25);
            changeX = -25;
            rotationComponent.setRotation(90);
        }

        boolean collidesWithPlayer = GameEngine.getEntityController().getEntities().stream()
                .filter(entity -> entity.hasComponent(CollidableComponent.class) && !entity.getUUID().equals(player.getUUID())).anyMatch(this::collidesWithPlayer);

        if(collidesWithPlayer) {
            positionComponent.addX(-changeX);
            positionComponent.addY(-changeY);
        }

        camera.update(player);
    }

    public boolean collidesWithPlayer(Entity entity) {
        if(!(entity.hasComponent(PositionComponent.class) && entity.hasComponent(SizeComponent.class))) {
            return false;
        }

        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
        SizeComponent sizeComponent = entity.getComponent(SizeComponent.class);

        PositionComponent playerPositionComponent = player.getComponent(PositionComponent.class);
        SizeComponent playerSizeComponent = player.getComponent(SizeComponent.class);

        return positionComponent.getX() < playerPositionComponent.getX() + playerSizeComponent.getWidth() &&
                positionComponent.getX() + sizeComponent.getWidth() > playerPositionComponent.getX() &&
                positionComponent.getY() < playerPositionComponent.getY() + playerSizeComponent.getHeight() &&
                positionComponent.getY() + sizeComponent.getHeight() > playerPositionComponent.getY();
    }
}
