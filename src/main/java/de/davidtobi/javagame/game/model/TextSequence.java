package de.davidtobi.javagame.game.model;

import de.davidtobi.javagame.game.data.TextSequences;

public class TextSequence {

    private final TextSequences textSequences;
    private int currentSequenceIndex = 0;

    public TextSequence(TextSequences textSequences) {
        this.textSequences = textSequences;
    }

    public TextSequences getTextSequences() {
        return textSequences;
    }

    public String getCurrentSequence() {
        return textSequences.getTextSequences()[currentSequenceIndex];
    }

    public void nextSequence() {
        currentSequenceIndex++;
    }

    public boolean hasNextSequence() {
        return currentSequenceIndex < textSequences.getTextSequences().length - 1;
    }
}
