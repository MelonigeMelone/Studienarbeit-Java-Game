package de.davidtobi.javagame.engine.ecs.renderersystem;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.ecs.component.ui.*;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.model.RendererSystem;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.util.RendererUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UIEntityRendererSystem extends RendererSystem {

    @Override
    public Class<? extends Component>[] getRequiredComponents() {
        return new Class[] {UIPositionComponent.class, UISizeComponent.class};
    }

    @Override
    public int getSystemPriority() {
        return 20;
    }

    @Override
    public void addEntity(Entity entity) {
        for (Class<? extends Component> componentClass : getRequiredComponents()) {
            if (!entity.hasComponent(componentClass)) {
                return;
            }
        }

        entities.add(entity);
        sortEntities();
    }

    @Override
    public void render(Graphics graphics) {
        entities.stream().filter(entity -> !entity.isDisabled())
                .forEach(entity -> renderEntity(graphics, entity));
    }

    private void renderEntity(Graphics graphics, Entity entity) {
        UIPositionComponent position = entity.getComponent(UIPositionComponent.class);
        UISizeComponent sizeComponent = entity.getComponent(UISizeComponent.class);

        if(position == null || sizeComponent == null) {
            return;
        }

        float scaleX = GameEngine.getDimensionHelper().getScaleX();
        float scaleY = GameEngine.getDimensionHelper().getScaleY();

        int width = (int) (sizeComponent.getWidth() * scaleX);
        int height = (int) (sizeComponent.getHeight() * scaleY);

        int posX = (int) (position.getX() * scaleX);
        int posY = (int) (position.getY() * scaleY);

        UITextureComponent textureComponent = entity.getComponent(UITextureComponent.class);
        if(textureComponent != null) {
            Texture texture = textureComponent.getTexture();

            UIHoverComponent hoverComponent = entity.getComponent(UIHoverComponent.class);
            if(hoverComponent != null && hoverComponent.isHovered()) {
                texture = hoverComponent.getHoverTexture();
            }

            graphics.drawImage(texture.getImage(), posX, posY, width, height, null);
        }

        UILabelComponent labelComponent = entity.getComponent(UILabelComponent.class);
        if (labelComponent != null) {
            Font scaledFont = labelComponent.getFont().deriveFont(labelComponent.getFont().getSize() * scaleX);
            graphics.setFont(scaledFont);
            FontMetrics fontMetrics = graphics.getFontMetrics();

            String labelValue = labelComponent.getValue();
            int textHeight = fontMetrics.getHeight();


            List<String> lines = new ArrayList<>();
            StringBuilder currentLine = new StringBuilder();
            String[] words = labelValue.split(" ");

            for (String word : words) {

                String testLine = currentLine + (currentLine.isEmpty() ? "" : " ") + word;
                int testLineWidth = fontMetrics.stringWidth(testLine);

                if (testLineWidth > width) {

                    lines.add(currentLine.toString());
                    currentLine = new StringBuilder(word);
                } else {
                    if (!currentLine.isEmpty()) {
                        currentLine.append(" ");
                    }
                    currentLine.append(word);
                }
            }

            if (!currentLine.isEmpty()) {
                lines.add(currentLine.toString());
            }

            int currentPosY = posY;
            for (String line : lines) {
                int lineWidth = fontMetrics.stringWidth(line);
                int labelPosX = RendererUtil.getAdjustedX(posX, width, lineWidth, labelComponent.getHorizontalAlignment());
                int labelPosY = RendererUtil.getAdjustedY(currentPosY, height, textHeight, fontMetrics, labelComponent.getVerticalAlignment());

                graphics.setColor(labelComponent.getColor());
                graphics.drawString(line, labelPosX, labelPosY);
                currentPosY += textHeight;
            }
        }
    }


    private void sortEntities() {
        entities.sort(Comparator.comparingInt(entity -> {
            UIPositionComponent position = entity.getComponent(UIPositionComponent.class);
            return (int) position.getZ();
        }));
    }
}
