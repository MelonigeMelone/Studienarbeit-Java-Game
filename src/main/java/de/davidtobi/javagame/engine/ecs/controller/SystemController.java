package de.davidtobi.javagame.engine.ecs.controller;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.ecs.model.RendererSystem;
import de.davidtobi.javagame.engine.ecs.model.System;
import de.davidtobi.javagame.engine.event.EventHandler;
import de.davidtobi.javagame.engine.input.InputHandler;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SystemController {

    private final EventHandler eventHandler;
    private final InputHandler inputHandler;

    private final Map<Class<? extends System>, System> systemMap = new HashMap<>();
    private final Map<Class<? extends RendererSystem>, RendererSystem> rendererSystemMap = new HashMap<>();

    public SystemController(EventHandler eventHandler, InputHandler inputHandler) {
        this.eventHandler = eventHandler;
        this.inputHandler = inputHandler;
    }

    public System getSystem(Class<? extends System> systemClass) {
        return systemMap.get(systemClass);
    }

    public void addSystem(System system) {
        if(system == null) {
            return;
        }

        systemMap.put(system.getClass(), system);
    }

    public void removeSystem(System system) {
        if(system == null) {
            return;
        }

        systemMap.remove(system.getClass());
    }

    public void updateSystems(float deltaTime) {

        int gameSpeed = GameEngine.getGameSettings().getGameSpeed();
        boolean isGamePaused = GameEngine.getGameSettings().isPaused();

        getSystemInPriorityOrder()
                .forEach(system -> system.update(deltaTime, gameSpeed, isGamePaused, eventHandler, inputHandler));
    }

    public RendererSystem getRendererSystem(Class<? extends RendererSystem> rendererSystemClass) {
        return rendererSystemMap.get(rendererSystemClass);
    }

    public void addRendererSystem(RendererSystem rendererSystem) {
        if(rendererSystem == null) {
            return;
        }

        rendererSystemMap.put(rendererSystem.getClass(), rendererSystem);
    }

    public void removeRendererSystem(RendererSystem rendererSystem) {
        if(rendererSystem == null) {
            return;
        }

        rendererSystemMap.remove(rendererSystem.getClass());
    }

    public void render(Graphics graphics) {
        getRendererSystemInPriorityOrder()
                .forEach(rendererSystem -> rendererSystem.render(graphics));
    }

    public Map<Class<? extends RendererSystem>, RendererSystem> getRendererSystemMap() {
        return rendererSystemMap;
    }

    public Map<Class<? extends System>, System> getSystemMap() {
        return systemMap;
    }

    public Stream<System> getSystemInPriorityOrder() {
        return systemMap.values().stream()
                .sorted((s1, s2) -> Integer.compare(
                        s2.getSystemPriority(), s1.getSystemPriority()
                ));
    }

    public Stream<RendererSystem> getRendererSystemInPriorityOrder() {
        return rendererSystemMap.values().stream()
                .sorted((s1, s2) -> Integer.compare(
                        s2.getSystemPriority(), s1.getSystemPriority()
                ));
    }
}
