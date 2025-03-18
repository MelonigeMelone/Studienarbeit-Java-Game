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
            renderTexture(entity, graphics, textureComponent, posX, posY, width, height);
        }

        UILabelComponent labelComponent = entity.getComponent(UILabelComponent.class);
        if (labelComponent != null) {
            renderLabel(labelComponent, graphics, posX, posY, width, height, scaleX);
        }

        UICodingComponent codingComponent = entity.getComponent(UICodingComponent.class);
        if(codingComponent != null) {
           renderCodingBox(codingComponent, graphics, posX, posY, width, height, scaleX);
        }
    }


    private void sortEntities() {
        entities.sort(Comparator.comparingInt(entity -> {
            UIPositionComponent position = entity.getComponent(UIPositionComponent.class);
            return (int) position.getZ();
        }));
    }

    private void renderTexture(Entity entity, Graphics graphics, UITextureComponent textureComponent, int posX, int posY, int width, int height) {
        Texture texture = textureComponent.getTexture();

        UIHoverComponent hoverComponent = entity.getComponent(UIHoverComponent.class);
        if(hoverComponent != null && hoverComponent.isHovered()) {
            texture = hoverComponent.getHoverTexture();
        }

        graphics.drawImage(texture.getImage(), posX, posY, width, height, null);
    }

    private void renderLabel(UILabelComponent labelComponent, Graphics graphics, int posX, int posY, int width, int height, float scaleX) {
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

    private void renderCodingBox(UICodingComponent codingComponent, Graphics graphics, int posX, int posY, int width, int height, float scaleX) {
        Font scaledFont = codingComponent.getFont().deriveFont(codingComponent.getFont().getSize() * scaleX);
        graphics.setFont(scaledFont);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        codingComponent.setFontMetrics(fontMetrics);

        String labelValue = codingComponent.getValue();
        int textHeight = fontMetrics.getHeight();

        // Split text by \n to handle line breaks properly
        String[] lines = labelValue.split("\n");

        int currentPosY = posY;
        for (String line : lines) {
            // Use a regex to split words but keep punctuation (e.g., operators, punctuation marks, etc.)
            String[] words = line.split("(?<=\\W)|(?=\\W)"); // Split words and keep non-word characters
            int currentPosX = posX;

            for (String word : words) {
                // Calculate the width of the word
                int wordWidth = fontMetrics.stringWidth(word);

                // Get the adjusted X and Y positions based on the alignment settings
                int labelPosX = RendererUtil.getAdjustedX(currentPosX, width, wordWidth, codingComponent.getHorizontalAlignment());
                int labelPosY = RendererUtil.getAdjustedY(currentPosY, height, textHeight, fontMetrics, codingComponent.getVerticalAlignment());

                // Use the SyntaxHighlighter to get the color for the current word
                Color color = codingComponent.getSyntaxHighlighter().getColor(word, Color.white);

                // Set the graphics color to the one returned by the highlighter
                graphics.setColor(color);

                // Draw the word at the calculated position
                graphics.drawString(word, labelPosX, labelPosY);

                // Update the X position for the next word
                currentPosX += wordWidth;
            }

            // Move down to the next line
            currentPosY += textHeight;
        }
    }

}
