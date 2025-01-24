package de.davidtobi.javagame.engine.util;

import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.VerticalAlignment;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RendererUtil {

    public static BufferedImage rotate(BufferedImage imageToRotate, double degrees) {
        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();

        BufferedImage newImageFromBuffer = new BufferedImage(widthOfImage, heightOfImage, typeOfImage);

        Graphics2D graphics2D = newImageFromBuffer.createGraphics();

        graphics2D.rotate(Math.toRadians(degrees), (double) widthOfImage / 2, (double) heightOfImage / 2);
        graphics2D.drawImage(imageToRotate, null, 0, 0);

        return newImageFromBuffer;
    }

    public static int getAdjustedX(int posX, int width, int textWidth, HorizontalAlignment horizontalAlignment) {
        return switch (horizontalAlignment) {
            case LEFT -> posX;
            case CENTER -> posX + (width - textWidth) / 2;
            case RIGHT -> posX + (width - textWidth);
        };
    }

    public static int getAdjustedY(int posY, int height, int textHeight, FontMetrics fontMetrics, VerticalAlignment verticalAlignment) {
        int ascent = fontMetrics.getAscent();
        int descent = fontMetrics.getDescent();

        return switch (verticalAlignment) {
            case TOP -> posY + ascent;
            case CENTER -> posY + (height - textHeight) / 2 + ascent;
            case BOTTOM -> posY + height - descent;
        };
    }
}
