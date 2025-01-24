package de.davidtobi.javagame.engine.ecs.model;

import java.util.ArrayList;
import java.util.List;

public class EntityGroup {

    private final List<Entity> entities;

    private boolean entitiesDisabled = false;

    public EntityGroup() {
        this.entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);

        entity.setDisabled(this.entitiesDisabled);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public void setDisabled(boolean disabled) {
        this.entitiesDisabled = disabled;
    }

    public boolean isEntitiesDisabled() {
        return entitiesDisabled;
    }
}
