package de.davidtobi.javagame.engine.event.model;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean cancelled);
}
