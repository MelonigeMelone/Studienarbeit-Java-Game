package de.davidtobi.javagame.engine.event.event.input;

public class KeyReleasedEvent extends AbstractKeyEvent {

    private final char keyChar;

    public KeyReleasedEvent(int keyCode, char keyChar) {
        super(keyCode);

        this.keyChar = keyChar;
    }

    public char getKeyChar() {
        return keyChar;
    }
}
