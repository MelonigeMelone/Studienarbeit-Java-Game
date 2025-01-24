package de.davidtobi.javagame.engine.ecs.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseSystem {

    protected final List<Entity> entities = new CopyOnWriteArrayList<>();

    public abstract Class<? extends Component>[] getRequiredComponents();

    public abstract int getSystemPriority();

    protected List<Entity> getEnabledEntities() {
        return entities.stream().filter(entity -> !entity.isDisabled()).toList();
    }

    public void addEntity(Entity entity) {
        for (Class<? extends Component> componentClass : getRequiredComponents()) {
            if (!entity.hasComponent(componentClass)) {
                return;
            }
        }

        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
}
