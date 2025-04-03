package de.davidtobi.javagame.game.textsequence.data;

public class TextSequenceMessage {

    private final TextNarrator narrator;
    private final String message;

    public TextSequenceMessage(TextNarrator narrator, String message) {
        this.narrator = narrator;
        this.message = message;
    }

    public TextNarrator getNarrator() {
        return narrator;
    }

    public String getMessage() {
        return message;
    }
}
