package de.davidtobi.javagame.engine.ecs.component;

import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.ShowMode;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.model.Component;

import java.awt.*;
import java.util.function.Supplier;

public class TextComponent implements Component {

    private String label;
    private Color color;
    private Font font;
    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment verticalAlignment;
    private float offsetX, offsetY;
    private ShowMode showMode;

    private Supplier<String> supplier;

    public TextComponent(String label, Color color, Font font, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, float offsetX, float offsetY, ShowMode showMode) {
        this.label = label;
        this.color = color;
        this.font = font;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.showMode = showMode;
    }

    public TextComponent(String label, Color color, Font font, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, float offsetX, float offsetY, ShowMode showMode, Supplier<String> supplier) {
        this.label = label;
        this.color = color;
        this.font = font;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.showMode = showMode;
        this.supplier = supplier;
    }

    public String getValue() {
        if(supplier == null) {
            return label;
        }

        return label + supplier.get();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public ShowMode getShowMode() {
        return showMode;
    }

    public void setShowMode(ShowMode showMode) {
        this.showMode = showMode;
    }

    public Supplier<String> getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier<String> supplier) {
        this.supplier = supplier;
    }
}
