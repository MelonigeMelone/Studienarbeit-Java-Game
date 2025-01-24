package de.davidtobi.javagame.engine.ecs.component.ui;

import de.davidtobi.javagame.engine.ecs.model.Component;

public class UIClickableComponent implements Component {

    private final Runnable runnable;

    public UIClickableComponent(Runnable runnable) {
        this.runnable = runnable;
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
