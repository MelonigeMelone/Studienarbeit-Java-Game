package de.davidtobi.javagame.engine.event.event.input;

import de.davidtobi.javagame.engine.event.model.Event;

public class AbstractKeyEvent extends Event {

    private final int keyCode;

    public AbstractKeyEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
