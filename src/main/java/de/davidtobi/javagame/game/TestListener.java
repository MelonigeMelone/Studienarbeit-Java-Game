package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.event.event.input.KeyPressedEvent;
import de.davidtobi.javagame.engine.event.event.input.KeyReleasedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;

import java.awt.event.KeyEvent;

public class TestListener implements Listener {

    private final Entity player;

    public TestListener(Entity player) {
        this.player = player;
    }

    @EventHandler
    public void onInput(KeyPressedEvent event) {
        VelocityComponent velocityComponent = player.getComponent(VelocityComponent.class);

        if(event.getKeyCode() == KeyEvent.VK_W) {
            velocityComponent.set(0, -25, 0);
        } else if(event.getKeyCode() == KeyEvent.VK_S) {
            velocityComponent.set(0, 25, 0);
        } else if(event.getKeyCode() == KeyEvent.VK_A) {
            velocityComponent.set(-25, 0, 0);
        } else if(event.getKeyCode() == KeyEvent.VK_D) {
            velocityComponent.set(25, 0, 0);
        } else {
            velocityComponent.set(0,0,0);
        }
    }
}
