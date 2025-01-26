package de.davidtobi.javagame.engine.ecs.component;

import de.davidtobi.javagame.engine.ecs.model.Component;

public class PositionComponent implements Component {
    private float x,y, z;

    public PositionComponent(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void addX(float x) {
        this.x += x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addY(float y) {
        this.y += y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void addZ(float z) {
        this.z += z;
    }
}
