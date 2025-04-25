package de.davidtobi.javagame.engine.event.event.input;

public class KeyPressedEvent extends AbstractKeyEvent {

    private final char keyChar;

    public KeyPressedEvent(int keyCode, char keyChar) {
        super(keyCode);

        this.keyChar = keyChar;
    }

    public char getKeyChar() {
        return keyChar;
    }
}
