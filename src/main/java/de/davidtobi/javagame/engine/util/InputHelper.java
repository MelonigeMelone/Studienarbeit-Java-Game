package de.davidtobi.javagame.engine.util;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.SizeComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UIPositionComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UISizeComponent;

public class InputHelper {

    public static boolean isMouseOverUIEntity(float mouseX, float mouseY, UIPositionComponent positionComponent, UISizeComponent sizeComponent) {
        return isMouseOverEntity(mouseX, mouseY, positionComponent.getX(), positionComponent.getY(), sizeComponent.getWidth(), sizeComponent.getHeight(), true);
    }

    public static boolean isMouseOverEntity(float mouseX, float mouseY, PositionComponent positionComponent, SizeComponent sizeComponent) {
        return isMouseOverEntity(mouseX, mouseY, positionComponent.getX(), positionComponent.getY(), sizeComponent.getWidth(), sizeComponent.getHeight(), false);
    }

    private static boolean isMouseOverEntity(float mouseX, float mouseY, float posX, float posY, float width, float height, boolean isUI) {
        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();
        float scaleX = dimensionHelper.getScaleX();
        float scaleY = dimensionHelper.getScaleY();

        int entityX = (int) (posX * scaleX);
        int entityY = (int) (posY * scaleY);
        int entityWidth = (int) (width * scaleX);
        int entityHeight = (int) (height * scaleY);

        int centerX = isUI ? entityX : (int) (entityX - (float) entityWidth / 2);
        int centerY = isUI ? entityY : (int) (entityY - (float) entityHeight / 2);

        return isMouseOverEntity(mouseX, mouseY, centerX, centerY, entityWidth, entityHeight);
    }

    private static boolean isMouseOverEntity(float mouseX, float mouseY, int entityX, int entityY, int entityWidth, int entityHeight) {
        return mouseX >= entityX && mouseX <= (entityX + entityWidth) && mouseY >= entityY && mouseY <= (entityY + entityHeight);
    }

}
