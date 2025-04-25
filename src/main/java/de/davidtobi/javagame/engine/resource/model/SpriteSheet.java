package de.davidtobi.javagame.engine.resource.model;

import de.davidtobi.javagame.engine.log.EngineLogger;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SpriteSheet implements Resource {

    private BufferedImage image;

    @Override
    public void load(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                EngineLogger.log(EngineLoggerLevel.ERROR, "Die Resource mit dem Pfdad " + path + " konnte nicht gefunden werden");
                return;
            }

            image = ImageIO.read(is);
        } catch (IOException exception) {
            EngineLogger.log(EngineLoggerLevel.ERROR, "Beim Laden der Resource mit dem Pfdad " +
                    path + " ist ein Fehler aufgetreten", exception);
        }
    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

    public BufferedImage getImage() {
        return image;
    }
}
