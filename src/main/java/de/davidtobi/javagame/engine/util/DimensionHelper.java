package de.davidtobi.javagame.engine.util;

import java.awt.*;

public class DimensionHelper {

    private final Dimension defaultDimension;
    private Dimension currentDimension;

    public DimensionHelper(Dimension defaultDimension) {
        this.defaultDimension = defaultDimension;
        this.currentDimension = defaultDimension;
    }

    public Dimension getCurrentDimension() {
        return currentDimension;
    }


    public void setDimension(Dimension dimension) {
        this.currentDimension = dimension;
    }

    public float getScaleX() {
        return (float) currentDimension.getWidth() / (float) defaultDimension.getWidth();
    }

    public float getScaleY() {
        return (float) currentDimension.getHeight() / (float) defaultDimension.getHeight();
    }

    public float getCenteredX(float width) {
        return (defaultDimension.width - width) / 2;
    }

    public float getCenteredY(float height) {
        return (defaultDimension.height - height) / 2;
    }
}
