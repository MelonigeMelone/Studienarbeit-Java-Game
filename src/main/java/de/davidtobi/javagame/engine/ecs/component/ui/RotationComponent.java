package de.davidtobi.javagame.engine.ecs.component.ui;

import de.davidtobi.javagame.engine.ecs.model.Component;

public class RotationComponent implements Component {

    private float rotation;

    public RotationComponent(float rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
