package de.davidtobi.javagame.game.textsequence.listener;

import de.davidtobi.javagame.engine.event.event.input.KeyPressedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.game.scene.BaseGameScene;

import java.awt.event.KeyEvent;

public class TextSequenceListener implements Listener {

    private final BaseGameScene baseGameScene;

    public TextSequenceListener(BaseGameScene baseGameScene) {
        this.baseGameScene = baseGameScene;
    }

    @EventHandler
    public void onInput(KeyPressedEvent event) {
        if(!baseGameScene.isInTextSequence()) {
            return;
        }

        if(!baseGameScene.getCurrentTextSequence().hasNextSequence()) {
            baseGameScene.closeTextSequence();
            return;
        }

        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
            baseGameScene.getCurrentTextSequence().nextSequence();
        }
    }
}
