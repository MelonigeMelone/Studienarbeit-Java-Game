package de.davidtobi.javagame.engine.camera;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;

public class Camera {

    private float x, y;
    private float width, height;

    public Camera(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update(Entity target) {
        PositionComponent position = target.getComponent(PositionComponent.class);
        if(position != null) {
            x = GameEngine.getDimensionHelper().getCenteredX(0) + position.getX();
            y = GameEngine.getDimensionHelper().getCenteredY(0) + position.getY();
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
