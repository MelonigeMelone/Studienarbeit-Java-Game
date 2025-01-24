package de.davidtobi.javagame.engine.scene;

import de.davidtobi.javagame.engine.ecs.controller.EntityController;
import de.davidtobi.javagame.engine.ecs.controller.SystemController;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.model.RendererSystem;
import de.davidtobi.javagame.engine.ecs.model.System;
import de.davidtobi.javagame.engine.event.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.engine.log.EngineLogger;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;

public class SceneController {
    private Scene currentScene;
    private final EventHandler eventHandler;
    private final EntityController entityController;
    private final SystemController systemController;

    public SceneController(EventHandler eventHandler, EntityController entityController, SystemController systemController) {
        this.eventHandler = eventHandler;
        this.entityController = entityController;
        this.systemController = systemController;
    }

    public void switchScene(Scene scene) {
        EngineLogger.log(EngineLoggerLevel.INFORMATION, "Es wird zu der Szene " + scene.getIdentifier() + " gewechselt");

        if(currentScene != null) {
            EngineLogger.log(EngineLoggerLevel.DEBUG, "Die Szene " + currentScene.getIdentifier() + " wird entladen");
            unloadScene(currentScene);
        }

        EngineLogger.log(EngineLoggerLevel.DEBUG, "Die Szene " + scene.getIdentifier() + " wird geladen");
        currentScene = scene;
        loadScene(scene);
    }

    private void loadScene(Scene scene) {
        for(Listener listener : scene.getListeners()) {
            eventHandler.registerListeners(listener);
        }

        for (de.davidtobi.javagame.engine.ecs.model.System system : scene.getSystems()) {
            systemController.addSystem(system);
        }

        for(RendererSystem rendererSystem : scene.getRendererSystems()) {
            systemController.addRendererSystem(rendererSystem);
        }

        for (Entity entity : scene.getEntities()) {
            entityController.addEntity(entity);
        }

        scene.setInitialized(true);
        scene.onEnter();
    }

    private void unloadScene(Scene scene) {
        for(Listener listener : scene.getListeners()) {
            eventHandler.unregisterListeners(listener);
        }

        for (System system : scene.getSystems()) {
            systemController.removeSystem(system);
        }

        for(RendererSystem rendererSystem : scene.getRendererSystems()) {
            systemController.removeRendererSystem(rendererSystem);
        }

        for (Entity entity : scene.getEntities()) {
            entityController.removeEntity(entity);
        }
    }

}
