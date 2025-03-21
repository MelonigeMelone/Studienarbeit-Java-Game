package de.davidtobi.javagame.game.textsequence.model;

import de.davidtobi.javagame.game.textsequence.data.TextSequenceMessage;
import de.davidtobi.javagame.game.textsequence.data.TextSequences;

public class TextSequence {

    private final TextSequences textSequences;
    private int currentSequenceIndex = 0;

    public TextSequence(TextSequences textSequences) {
        this.textSequences = textSequences;
    }

    public TextSequenceMessage getCurrentMessage() {
        return textSequences.getTextSequenceMessages()[currentSequenceIndex];
    }

    public void nextSequence() {
        currentSequenceIndex++;
    }

    public boolean hasNextSequence() {
        return currentSequenceIndex < textSequences.getSequenceCount() - 1;
    }
}
