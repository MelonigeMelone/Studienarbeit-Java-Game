package de.davidtobi.javagame.engine.util;

import java.awt.*;

public class DimensionHelper {

    private final Dimension defaultDimension;
    private Dimension currentdimension;

    public DimensionHelper(Dimension defaultDimension) {
        this.defaultDimension = defaultDimension;
        this.currentdimension = defaultDimension;
    }

    public void setDimension(Dimension dimension) {
        this.currentdimension = dimension;
    }

    public float getScaleX() {
        return (float) currentdimension.getWidth() / (float) defaultDimension.getWidth();
    }

    public float getScaleY() {
        return (float) currentdimension.getHeight() / (float) defaultDimension.getHeight();
    }

    public float getCenteredX(float width) {
        return (defaultDimension.width - width) / 2;
    }

    public float getCenteredY(float height) {
        return (defaultDimension.height - height) / 2;
    }
}
