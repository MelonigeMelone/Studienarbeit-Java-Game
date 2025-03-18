package de.davidtobi.javagame.engine.ecs.component.ui;

import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.SyntaxHighlighter;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.model.Component;

import java.awt.*;
import java.util.function.Supplier;

public class UICodingComponent implements Component {

    private Color color;
    private Font font;

    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment verticalAlignment;

    private SyntaxHighlighter syntaxHighlighter;
    private Supplier<String> valueSupplier;

    private boolean isFocused;
    private FontMetrics fontMetrics;

    public UICodingComponent(Color color, Font font, HorizontalAlignment horizontalAlignment,
                             VerticalAlignment verticalAlignment, SyntaxHighlighter syntaxHighlighter, Supplier<String> valueSupplier) {
        this.color = color;
        this.font = font;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.syntaxHighlighter = syntaxHighlighter;
        this.valueSupplier = valueSupplier;
        this.isFocused = false;
    }

    public String getValue() {
        if(valueSupplier == null) {
            return "";
        }

        return valueSupplier.get();
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

    public SyntaxHighlighter getSyntaxHighlighter() {
        return syntaxHighlighter;
    }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public FontMetrics getFontMetrics() {
        return fontMetrics;
    }

    public void setFontMetrics(FontMetrics fontMetrics) {
        this.fontMetrics = fontMetrics;
    }
}
