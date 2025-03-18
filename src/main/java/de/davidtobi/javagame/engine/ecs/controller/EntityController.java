package de.davidtobi.javagame.engine.ecs.controller;

import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.model.RendererSystem;

import java.util.ArrayList;
import java.util.List;

public class EntityController {

    private final SystemController systemController;
    private final List<Entity> entities = new ArrayList<>();

    public EntityController(final SystemController systemController) {
        this.systemController = systemController;
    }

    public void addEntity(Entity entity) {
        if(entity == null) {
            return;
        }

        entities.add(entity);
        systemController.getSystemMap().values().forEach(system -> system.addEntity(entity));
        systemController.getRendererSystemMap().values().forEach(rendererSystem -> rendererSystem.addEntity(entity));
    }

    public void removeEntity(Entity entity) {
        if(entity == null) {
            return;
        }

        entities.remove(entity);
        systemController.getSystemMap().values().forEach(system -> system.removeEntity(entity));
        systemController.getRendererSystemMap().values().forEach(rendererSystem -> rendererSystem.removeEntity(entity));
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
