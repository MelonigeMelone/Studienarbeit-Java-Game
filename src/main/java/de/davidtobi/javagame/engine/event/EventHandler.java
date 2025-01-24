package de.davidtobi.javagame.engine.event;


import de.davidtobi.javagame.engine.event.model.*;
import de.davidtobi.javagame.engine.log.EngineLogger;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventHandler {

    private final HashMap<Class<? extends Event>, CopyOnWriteArrayList<RegisteredListener>> registeredListeners = new HashMap<>();

    public void registerListeners(Object listenerClassInstance) {
        if(!(listenerClassInstance instanceof Listener)) {
            EngineLogger.log(EngineLoggerLevel.ERROR, "Die Klasse " + listenerClassInstance.getClass().getSimpleName() +
                    " konnte nicht registriert werden, da sie kein Listener ist");
            return;
        }

        for (Method method : listenerClassInstance.getClass().getMethods()) {
            registerMethod(listenerClassInstance, method);
        }
    }

    public void unregisterListeners(Object listenerClassInstance) {
        for (CopyOnWriteArrayList<RegisteredListener> listenerList : registeredListeners.values()) {
            for (int i = 0; i < listenerList.size(); i++) {
                if (listenerList.get(i).getListenerClassInstance() == listenerClassInstance) {
                    listenerList.remove(i);
                    i -= 1;
                }
            }
        }
    }

    public void unregisterListenersOfEvent(Class<? extends Event> eventClass) {
        registeredListeners.get(eventClass).clear();
    }

    public void callEvent(Event event) {
        EngineLogger.log(EngineLoggerLevel.DEBUG, "Das Event " + event.getName() + " wird aufgerufen");
        Arrays.stream(EventPriority.values()).forEach(eventPriority -> dispatchEvent(event, eventPriority));
    }

    @SuppressWarnings("unchecked")
    private void registerMethod(Object listenerClassInstance, Method method) {

        if (!method.isAnnotationPresent(de.davidtobi.javagame.engine.event.model.EventHandler.class)) {
            return;
        }

        if (method.getParameterCount() != 1) {
            EngineLogger.log(EngineLoggerLevel.ERROR,"Die Methode " + method.getName() +
                    " konnte nicht registriert werden, falsche Anzahl an Argumenten" );
            return;
        }

        if (!Event.class.isAssignableFrom(method.getParameterTypes()[0])) {
            EngineLogger.log(EngineLoggerLevel.ERROR, "Die Methode " + method.getName() +
                    " konnte nicht registriert werden, falscher Parameter");
            return;
        }

        Class<? extends Event> eventType = (Class<? extends Event>) method.getParameterTypes()[0];
        EventPriority priority = method.getAnnotation(de.davidtobi.javagame.engine.event.model.EventHandler.class).priority();

        RegisteredListener registeredListener = new RegisteredListener(listenerClassInstance, method, priority);
        addListener(eventType, registeredListener);
    }

    private void addListener(Class<? extends Event> eventType, RegisteredListener registeredListener) {
        if (!registeredListeners.containsKey(eventType)) {
            registeredListeners.put(eventType, new CopyOnWriteArrayList<>());
        }

        registeredListeners.get(eventType).add(registeredListener);
    }

    private void dispatchEvent(Event event, EventPriority priority) {

        if(event instanceof Cancellable cancellable) {
            if(cancellable.isCancelled()) {
                return;
            }
        }

        CopyOnWriteArrayList<RegisteredListener> listeners = registeredListeners.get(event.getClass());

        if (listeners != null) {
            for (RegisteredListener registeredListener : listeners) {
                if (registeredListener.getEventPriority() == priority) {
                    try {
                        registeredListener.getListenerMethod().setAccessible(true);
                        registeredListener.getListenerMethod().invoke(registeredListener.getListenerClassInstance(), event);
                    } catch (IllegalAccessException | InvocationTargetException exception) {
                        EngineLogger.log(EngineLoggerLevel.ERROR, "Bei dem Ausführen des Events " + event.getName() + " ist ein Fehler aufgetreten. " +
                                "(" + registeredListener.getListenerClassInstance().getClass().getSimpleName() + " : " + registeredListener.getListenerMethod().getName() + ")", exception);
                    }
                }
            }
        }
    }

    public HashMap<Class<? extends Event>, CopyOnWriteArrayList<RegisteredListener>> getRegisteredListeners() {
        return registeredListeners;
    }
}
