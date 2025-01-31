package de.davidtobi.javagame.engine.event.event.ui;

import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.event.model.Event;

public class UIElementClickedEvent extends Event {

    private final Entity clickedEntity;

    public UIElementClickedEvent(Entity clickedEntity) {
        this.clickedEntity = clickedEntity;
    }

    public Entity getClickedEntity() {
        return clickedEntity;
    }
}
