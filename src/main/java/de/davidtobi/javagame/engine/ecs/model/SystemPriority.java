package de.davidtobi.javagame.engine.ecs.model;

public enum SystemPriority {
    LATEST(0),
    LAST(1),
    NORMAL(2),
    FIRST(3),
    HIGHEST(4);

    private final int priority;

    SystemPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
