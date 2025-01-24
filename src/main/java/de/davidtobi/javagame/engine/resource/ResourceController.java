package de.davidtobi.javagame.engine.resource;

import de.davidtobi.javagame.engine.log.EngineLogger;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;
import de.davidtobi.javagame.engine.resource.model.Resource;

import java.util.HashMap;
import java.util.Map;

public class ResourceController {

    private final Map<String, Resource> resourceCache;

    public ResourceController() {
        resourceCache = new HashMap<>();
    }

    public <T extends Resource> T loadResource(String path, Class<T> resourceType) {
        if (resourceCache.containsKey(path)) {
            return resourceType.cast(resourceCache.get(path));
        }

        try {
            T resource = resourceType.getDeclaredConstructor().newInstance();
            resource.load(path);
            resourceCache.put(path, resource);
            return resource;
        } catch (Exception exception) {
            EngineLogger.log(EngineLoggerLevel.ERROR, "Beim Laden der Resource mit dem Pfad " +
                    path + " ist ein Fehler aufgetreten", exception);

            return null;
        }
    }

    public void unloadResource(String path) {
        resourceCache.remove(path);
    }

    public void clearResources() {
        resourceCache.clear();
    }
}
