package de.davidtobi.javagame.game.world;

import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.game.world.data.LevelBlockData;
import de.davidtobi.javagame.game.world.data.OldLevelBlockType;

import java.util.function.Supplier;

public class LevelBlock {

    private final int x, y;
    private final LevelBlockData levelBlockData;
    private final boolean collidable;
    private final boolean interactable;
    private Supplier<Scene> newScene;
    private Entity entity;

    public LevelBlock(int x, int y, LevelBlockData levelBlockData, boolean collidable) {
        this.x = x;
        this.y = y;
        this.levelBlockData = levelBlockData;
        this.collidable = collidable;
        this.interactable = false;
    }

    public LevelBlock(int x, int y, LevelBlockData levelBlockData, boolean collidable, Supplier<Scene> newScene) {
        this.x = x;
        this.y = y;
        this.levelBlockData = levelBlockData;
        this.collidable = collidable;
        this.interactable = true;
        this.newScene = newScene;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public LevelBlockData getLevelBlockData() {
        return levelBlockData;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public boolean isInteractable() {
        return interactable;
    }

    public Supplier<Scene> getNewScene() {
        return newScene;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
