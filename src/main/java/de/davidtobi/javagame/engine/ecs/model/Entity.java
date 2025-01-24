package de.davidtobi.javagame.engine.ecs.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Entity {
    private final UUID uuid;
    private final String entityTag;
    private final Map<Class<? extends Component>, Component> components;
    private boolean disabled = false;

    public Entity(String entityTag, List<Component> components) {
        this.entityTag = entityTag;
        this.uuid = UUID.randomUUID();
        this.components = new HashMap<>();

        if(components == null) {
            return;
        }

        for (Component component : components) {
            this.components.put(component.getClass(), component);
        }
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getEntityTag() {
        return entityTag;
    }

    public boolean hasComponent(Class<? extends Component> clazz) {
        return components.containsKey(clazz);
    }

    public void addComponent(Component component) {
        components.put(component.getClass(), component);
    }

    public void removeComponent(Class<? extends Component> clazz) {
        components.remove(clazz);
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
