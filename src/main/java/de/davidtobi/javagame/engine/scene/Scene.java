package de.davidtobi.javagame.engine.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.model.RendererSystem;
import de.davidtobi.javagame.engine.ecs.model.System;
import de.davidtobi.javagame.engine.event.model.Listener;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private final String identifier;

    private final List<Listener> listeners;

    private final List<Entity> entities;
    private final List<System> systems;
    private final List<RendererSystem> rendererSystems;

    private boolean isInitialized = false;

    public Scene(String identifier) {
        this.identifier = identifier;
        this.listeners = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.systems = new ArrayList<>();
        this.rendererSystems = new ArrayList<>();
    }

    public abstract void onEnter();

    public String getIdentifier() {
        return identifier;
    }

    public List<Listener> getListeners() {
        return listeners;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<System> getSystems() {
        return systems;
    }

    public List<RendererSystem> getRendererSystems() {
        return rendererSystems;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);

        if(isInitialized) {
            GameEngine.getEntityController().addEntity(entity);
        }
    }

    public void addSystem(System system) {
        systems.add(system);
    }

    public void addRendererSystem(RendererSystem rendererSystem) {
        rendererSystems.add(rendererSystem);
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }
}
