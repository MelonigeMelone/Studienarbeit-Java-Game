package de.davidtobi.javagame.engine.ecs.renderersystem;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.data.ShowMode;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.SizeComponent;
import de.davidtobi.javagame.engine.ecs.component.TextComponent;
import de.davidtobi.javagame.engine.ecs.component.TextureComponent;
import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.RotationComponent;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.model.RendererSystem;
import de.davidtobi.javagame.engine.util.RendererUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityRendererSystem extends RendererSystem {

    private final Camera camera;

    public EntityRendererSystem(Camera camera) {
        this.camera = camera;
    }

    @Override
    public Class<? extends Component>[] getRequiredComponents() {
        return new Class[0];
    }

    @Override
    public int getSystemPriority() {
        return 30;
    }

    @Override
    public void render(Graphics graphics) {
        entities.stream().filter(entity -> !entity.isDisabled())
                .forEach(entity -> renderEntity(graphics, entity));
    }

    private void renderEntity(Graphics graphics, Entity entity) {
        PositionComponent position = entity.getComponent(PositionComponent.class);
        SizeComponent sizeComponent = entity.getComponent(SizeComponent.class);

        if (position != null && sizeComponent != null) {
            TextureComponent textureComponent = entity.getComponent(TextureComponent.class);

            float scaleX = GameEngine.getDimensionHelper().getScaleX();
            float scaleY = GameEngine.getDimensionHelper().getScaleY();

            int width = (int) (sizeComponent.getWidth() * scaleX);
            int height = (int) (sizeComponent.getHeight() * scaleY);

            float posX = (camera.getX() - position.getX()) * scaleX;
            float posY = (camera.getY() - position.getY()) * scaleY;

            int centerX = (int) (posX - (float) width / 2);
            int centerY = (int) (posY - (float) height / 2);


            if (textureComponent != null) {

                BufferedImage image = textureComponent.getTexture().getImage();

                VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);
                if (velocityComponent != null) {
                    float rotation = 90f + (float) Math.toDegrees(Math.atan2(velocityComponent.getVy(), velocityComponent.getVx()));
                    image = RendererUtil.rotate(textureComponent.getTexture().getImage(), rotation);
                }

                RotationComponent rotationComponent = entity.getComponent(RotationComponent.class);
                if (rotationComponent != null) {
                    image = RendererUtil.rotate(textureComponent.getTexture().getImage(), rotationComponent.getRotation());
                }

                graphics.drawImage(image, centerX, centerY, width, height, null);
            } else {
                graphics.setColor(Color.WHITE);
                graphics.drawRect(centerX, centerY, width, height);
            }




            TextComponent textComponent = entity.getComponent(de.davidtobi.javagame.engine.ecs.component.TextComponent.class);
            if (textComponent != null && (textComponent.getShowMode().equals(ShowMode.ALWAYS) || (
                    textComponent.getShowMode().equals(ShowMode.ON_HOVER)
            ))) {

                Font scaledFont = textComponent.getFont().deriveFont(textComponent.getFont().getSize() * scaleX);
                graphics.setFont(scaledFont);
                FontMetrics fontMetrics = graphics.getFontMetrics();

                String labelValue = textComponent.getValue();

                int textWidth = fontMetrics.stringWidth(labelValue);
                int textHeight = fontMetrics.getHeight();

                float textPosX = (position.getX() + textComponent.getOffsetX()) * scaleX;
                float textPosY = (position.getY() + textComponent.getOffsetY()) * scaleY;

                int textCenterX = (int) (textPosX - (float) width / 2);
                int textCenterY = (int) (textPosY - (float) height / 2);

                int labelPosX = RendererUtil.getAdjustedX(textCenterX, width, textWidth, textComponent.getHorizontalAlignment());
                int labelPosY = RendererUtil.getAdjustedY(textCenterY, height, textHeight, fontMetrics, textComponent.getVerticalAlignment());

                graphics.setColor(textComponent.getColor());
                graphics.drawString(labelValue, labelPosX, labelPosY);

            }

        }
    }
}
