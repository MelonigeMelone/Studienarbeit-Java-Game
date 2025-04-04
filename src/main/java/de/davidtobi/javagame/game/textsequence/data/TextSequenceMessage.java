package de.davidtobi.javagame.game.textsequence.data;

public class TextSequenceMessage {

    private static int TICK_SPEED = 20;

    private final TextNarrator narrator;
    private final String message;

    private String currentDisplayedMessage;
    private int tickIndex = 0;
    private boolean currentDisplayedMessageFinished = false;

    public TextSequenceMessage(TextNarrator narrator, String message) {
        this.narrator = narrator;
        this.message = message;
        this.currentDisplayedMessage = "";
    }

    public TextNarrator getNarrator() {
        return narrator;
    }

    public String getMessage() {
        return message;
    }

    public String getCurrentDisplayedMessage() {
        return currentDisplayedMessage;
    }

    public void finishCurrentDisplayedMessage() {
        currentDisplayedMessage = message;
        currentDisplayedMessageFinished = true;
    }

    public void tickCurrentDisplayedMessage() {
        if (currentDisplayedMessageFinished) {
            return;
        }

        tickIndex++;
        if (tickIndex % TICK_SPEED != 0) {
            return;
        }

        if (currentDisplayedMessage.length() < message.length()) {
            currentDisplayedMessage += message.charAt(currentDisplayedMessage.length());
        } else {
            currentDisplayedMessageFinished = true;
        }
    }

    public boolean isCurrentDisplayedMessageFinished() {
        return currentDisplayedMessageFinished;
    }
}
