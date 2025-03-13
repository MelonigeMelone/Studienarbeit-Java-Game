package de.davidtobi.javagame.engine.ecs.component;

import de.davidtobi.javagame.engine.ecs.model.Component;

public class VelocityComponent implements Component {

    private float vx, vy, vz;

    public VelocityComponent(float vx, float vy, float vz) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    public boolean isMoving() {
        return vx != 0 || vy != 0 || vz != 0;
    }

    public void set(float vx, float vy, float vz) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    public void add(float vx, float vy, float vz) {
        this.vx += vx;
        this.vy += vy;
        this.vz += vz;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getVz() {
        return vz;
    }

    public void setVz(float vz) {
        this.vz = vz;
    }
}
