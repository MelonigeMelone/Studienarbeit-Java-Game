package de.davidtobi.javagame.engine.event.model;

public abstract class Event {

    public String getName() {
        return getClass().getSimpleName();
    }
}
