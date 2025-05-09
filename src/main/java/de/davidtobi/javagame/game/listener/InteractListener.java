package de.davidtobi.javagame.game.listener;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.event.event.input.KeyPressedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.game.scene.CodingScene;
import de.davidtobi.javagame.game.scene.LevelScene;
import de.davidtobi.javagame.game.world.LevelBlock;
import de.davidtobi.javagame.game.world.data.TriggerType;

import java.awt.event.KeyEvent;
import java.util.Optional;

public class InteractListener implements Listener {

    private final LevelScene levelScene;

    public InteractListener(LevelScene levelScene) {
        this.levelScene = levelScene;
    }

    @EventHandler
    public void onInput(KeyPressedEvent event) {
        if(levelScene.isInTextSequence()) {
            return;
        }

        PositionComponent positionComponent = levelScene.getPlayer().getComponent(PositionComponent.class);

        Optional<LevelBlock> optionalLevelBlock = levelScene.getLevel().getDecorations().stream().filter(levelBlock -> levelBlock.isInteractable() &&
                checkDistance(levelBlock, positionComponent) && levelBlock.getTriggerType().equals(TriggerType.INTERACT)).findFirst();

        if(optionalLevelBlock.isEmpty()) {
            return;
        }


        if(event.getKeyCode() == KeyEvent.VK_E) {
            GameEngine.getSceneController().switchScene(optionalLevelBlock.get().getNewScene().get());
        }
    }

    private boolean checkDistance(LevelBlock levelBlock, PositionComponent positionComponent) {
        Entity entity = levelBlock.getEntity();
        PositionComponent entityPositionComponent = entity.getComponent(PositionComponent.class);
        return Math.abs(positionComponent.getX() - entityPositionComponent.getX()) <= 60 &&
                Math.abs(positionComponent.getY() - entityPositionComponent.getY()) <= 60;
    }
}
