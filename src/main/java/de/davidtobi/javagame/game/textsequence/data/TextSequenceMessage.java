package de.davidtobi.javagame.game.textsequence.data;

public class TextSequenceMessage {

    private final String narrator;
    private final String message;

    public TextSequenceMessage(String narrator, String message) {
        this.narrator = narrator;
        this.message = message;
    }

    public String getNarrator() {
        return narrator;
    }

    public String getMessage() {
        return message;
    }
}
