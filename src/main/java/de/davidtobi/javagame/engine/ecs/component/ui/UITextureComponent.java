package de.davidtobi.javagame.engine.ecs.component.ui;

import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.resource.model.Texture;

public class UITextureComponent implements Component {

    private Texture texture;

    public UITextureComponent(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
