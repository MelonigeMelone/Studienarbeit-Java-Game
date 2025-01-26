package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.RotationComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.event.event.input.KeyPressedEvent;
import de.davidtobi.javagame.engine.event.event.input.KeyReleasedEvent;
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

        if(event.getKeyCode() == KeyEvent.VK_W) {
            positionComponent.addY(25);
            rotationComponent.setRotation(0);
        } else if(event.getKeyCode() == KeyEvent.VK_S) {
            positionComponent.addY(-25);
            rotationComponent.setRotation(180);
        } else if(event.getKeyCode() == KeyEvent.VK_A) {
            positionComponent.addX(25);
            rotationComponent.setRotation(270);
        } else if(event.getKeyCode() == KeyEvent.VK_D) {
            positionComponent.addX(-25);
            rotationComponent.setRotation(90);
        }

        camera.update(player);
    }
}
