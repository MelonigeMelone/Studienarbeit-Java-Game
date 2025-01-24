package de.davidtobi.javagame.engine.ecs.component.ui;

import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.model.Component;

import java.awt.*;
import java.util.function.Supplier;

public class UILabelComponent implements Component {

    private String text;
    private Color color;
    private Font font;

    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment verticalAlignment;

    private Supplier<String> valueSupplier;

    public UILabelComponent(String text, Color color, Font font, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.text = text;
        this.color = color;
        this.font = font;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    public UILabelComponent(String text, Color color, Font font, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, Supplier<String> valueSupplier) {
        this.text = text;
        this.color = color;
        this.font = font;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.valueSupplier = valueSupplier;
    }

    public String getValue() {
        if(valueSupplier == null) {
            return text;
        }

        return text + valueSupplier.get();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Supplier<String> getValueSupplier() {
        return valueSupplier;
    }

    public void setValueSupplier(Supplier<String> valueSupplier) {
        this.valueSupplier = valueSupplier;
    }
}
