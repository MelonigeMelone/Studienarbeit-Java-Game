package de.davidtobi.javagame.game.codingtask;

import de.davidtobi.javagame.game.textsequence.data.TextSequences;

import java.util.HashMap;

public abstract class CodingTask {

    protected final String id;
    protected final String mainTask;
    protected final CodingTaskClass[] codingTaskClasses;
    protected final HashMap<CodingTaskHelpSequenceType, TextSequences> helpTextSequences;

    public CodingTask(String id, String mainTask, CodingTaskClass[] codingTaskClasses, HashMap<CodingTaskHelpSequenceType, TextSequences> helpTextSequences) {
        this.id = id;
        this.mainTask = mainTask;
        this.codingTaskClasses = codingTaskClasses;
        this.helpTextSequences = helpTextSequences;
    }

    public abstract boolean compiledSuccessfully() throws Exception;

    public String getId() {
        return id;
    }

    public String getMainTask() {
        return mainTask;
    }

    public CodingTaskClass[] getCodingTaskClasses() {
        return codingTaskClasses;
    }

    public boolean hasHelpTextSequences() {
        return !helpTextSequences.isEmpty();
    }

    public HashMap<CodingTaskHelpSequenceType, TextSequences> getHelpTextSequences() {
        return helpTextSequences;
    }

}
