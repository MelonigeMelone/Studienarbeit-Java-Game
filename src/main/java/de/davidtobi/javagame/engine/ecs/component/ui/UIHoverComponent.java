package de.davidtobi.javagame.engine.ecs.component.ui;

import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.resource.model.Texture;

public class UIHoverComponent implements Component {

    private Texture hoverTexture;
    private boolean isHovered = false;

    public UIHoverComponent(Texture hoverTexture) {
        this.hoverTexture = hoverTexture;
    }

    public Texture getHoverTexture() {
        return hoverTexture;
    }

    public void setHoverTexture(Texture hoverTexture) {
        this.hoverTexture = hoverTexture;
    }

    public boolean isHovered() {
        return isHovered;
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }
}
