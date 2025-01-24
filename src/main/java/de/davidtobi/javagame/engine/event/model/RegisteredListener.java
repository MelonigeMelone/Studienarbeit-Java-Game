package de.davidtobi.javagame.engine.event.model;

import java.lang.reflect.Method;

public class RegisteredListener {

    private final Object listenerClassInstance;

    private final Method listenerMethod;

    private final EventPriority eventPriority;

    public RegisteredListener(final Object listenerClassInstance, final Method listenerMethod, final EventPriority eventPriority) {
        this.listenerClassInstance = listenerClassInstance;
        this.listenerMethod = listenerMethod;
        this.eventPriority = eventPriority;
    }

    public Object getListenerClassInstance() {
        return listenerClassInstance;
    }

    public Method getListenerMethod() {
        return listenerMethod;
    }

    public EventPriority getEventPriority() {
        return eventPriority;
    }
}
