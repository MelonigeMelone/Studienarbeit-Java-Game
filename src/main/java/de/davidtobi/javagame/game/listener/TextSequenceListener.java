package de.davidtobi.javagame.game.listener;

import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.RotationComponent;
import de.davidtobi.javagame.engine.event.event.input.KeyPressedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.game.scene.LevelScene;

import java.awt.event.KeyEvent;

public class TextSequenceListener implements Listener {

    private final LevelScene levelScene;

    public TextSequenceListener(LevelScene levelScene) {
        this.levelScene = levelScene;
    }

    @EventHandler
    public void onInput(KeyPressedEvent event) {
        if(!levelScene.isInTextSequence()) {
            return;
        }

        if(!levelScene.getCurrentTextSequence().hasNextSequence()) {
            levelScene.closeTextSequence();
            return;
        }

        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
            levelScene.getCurrentTextSequence().nextSequence();
        }
    }
}
