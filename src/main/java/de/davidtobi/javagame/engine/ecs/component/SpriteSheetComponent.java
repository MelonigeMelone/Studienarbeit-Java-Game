package de.davidtobi.javagame.engine.ecs.component;

import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.resource.model.Texture;

import java.awt.image.BufferedImage;

public class SpriteSheetComponent implements Component {

    private Texture texture;

    private int spriteWidth;
    private int spriteHeight;
    private int spriteSheetAnimationCounter = 0;

    public SpriteSheetComponent(Texture texture, int spriteWidth, int spriteHeight) {
        this.texture = texture;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getSpriteCountX() {
        return texture.getImage().getWidth() / spriteWidth;
    }

    public BufferedImage getSprite(int x, int y) {
        if(this.texture.getImage() == null) {
            return null;
        }
        return texture.getImage().getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
    }

    public int getSpriteSheetAnimationCounter() {
        return spriteSheetAnimationCounter;
    }

    public void incrementSpriteSheetAnimationCounter() {
        spriteSheetAnimationCounter++;

        if(spriteSheetAnimationCounter >= getSpriteCountX()-1) {
            spriteSheetAnimationCounter = 0;
        }

    }
}
