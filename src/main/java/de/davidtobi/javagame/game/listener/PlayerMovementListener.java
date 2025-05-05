package de.davidtobi.javagame.game.listener;

import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.RotationComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.event.event.input.KeyPressedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.EventPriority;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.game.scene.LevelScene;

import java.awt.event.KeyEvent;

public class PlayerMovementListener implements Listener {

    private final int PLAYER_SPEED = 300;

    private final Camera camera;
    private final Entity player;
    private final LevelScene levelScene;

    public PlayerMovementListener(Camera camera, Entity player, LevelScene levelScene) {
        this.player = player;
        this.camera = camera;
        this.levelScene = levelScene;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInput(KeyPressedEvent event) {
        if(levelScene.isInTextSequence()) {
            return;
        }

        VelocityComponent velocityComponent = player.getComponent(VelocityComponent.class);
        RotationComponent rotationComponent = player.getComponent(RotationComponent.class);

        if(event.getKeyCode() == KeyEvent.VK_W) {
            velocityComponent.set(0,PLAYER_SPEED, 0);
            rotationComponent.setRotation(0);;
        } else if(event.getKeyCode() == KeyEvent.VK_S) {
            velocityComponent.set(0,-PLAYER_SPEED, 0);
            rotationComponent.setRotation(180);
        } else if(event.getKeyCode() == KeyEvent.VK_A) {
            velocityComponent.set(PLAYER_SPEED,0, 0);
            rotationComponent.setRotation(270);
        } else if(event.getKeyCode() == KeyEvent.VK_D) {
            velocityComponent.set(-PLAYER_SPEED,0, 0);
            rotationComponent.setRotation(90);
        }

        camera.update(player);

    }
}
