package de.davidtobi.javagame.engine.ecs.component.ui;

import de.davidtobi.javagame.engine.ecs.model.Component;

public class UITextBoxComponent implements Component {
    private String text;
    private boolean isFocused;

    public UITextBoxComponent(String text) {
        this.text = text;
        this.isFocused = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        isFocused = focused;
    }

    public void toggleFocus() {
        isFocused = !isFocused;
    }
}
