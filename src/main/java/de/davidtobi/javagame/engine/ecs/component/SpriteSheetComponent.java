package de.davidtobi.javagame.engine.ecs.component;

import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.resource.model.Texture;

import java.awt.image.BufferedImage;

public class SpriteSheetComponent implements Component {

    private Texture texture;

    private int spriteWidth;
    private int spriteHeight;
    private final int x, y;

    public SpriteSheetComponent(Texture texture, int spriteWidth, int spriteHeight, int x, int y) {
        this.texture = texture;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.x = x;
        this.y = y;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getSpriteCountX() {
        return texture.getImage().getWidth() / spriteWidth;
    }

    public BufferedImage getSprite() {
        if(this.texture.getImage() == null) {
            return null;
        }
        return texture.getImage().getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
    }
}
