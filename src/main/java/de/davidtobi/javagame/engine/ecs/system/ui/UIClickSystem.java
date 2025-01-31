package de.davidtobi.javagame.engine.ecs.system.ui;

import de.davidtobi.javagame.engine.ecs.component.ui.UIClickableComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UIPositionComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UISizeComponent;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.System;
import de.davidtobi.javagame.engine.event.EventHandler;
import de.davidtobi.javagame.engine.event.event.ui.UIElementClickedEvent;
import de.davidtobi.javagame.engine.input.InputHandler;
import de.davidtobi.javagame.engine.math.model.Vector2D;
import de.davidtobi.javagame.engine.util.InputHelper;

public class UIClickSystem extends System {

    private boolean mouseWasPressed = false;

    @Override
    public Class<? extends Component>[] getRequiredComponents() {
        return new Class[] {UIPositionComponent.class, UISizeComponent.class, UIClickableComponent.class};
    }

    @Override
    public int getSystemPriority() {
        return 10;
    }

    @Override
    public void update(float deltaTime, int gameSpeed, boolean isPaused, EventHandler eventHandler, InputHandler inputHandler) {
        Vector2D mousePosition = inputHandler.getMousePos();
        if(mousePosition == null) {
            return;
        }

        boolean mousePressed = inputHandler.isMousePressed();

        if(!mouseWasPressed || mousePressed) {
            mouseWasPressed = mousePressed;
            return;
        }

        float mouseX = mousePosition.getX();
        float mouseY = mousePosition.getY();

        getEnabledEntities().forEach(entity -> {
            UIPositionComponent position = entity.getComponent(UIPositionComponent.class);
            UISizeComponent size = entity.getComponent(UISizeComponent.class);
            UIClickableComponent clickableComponent = entity.getComponent(UIClickableComponent.class);

            if(position != null && size != null && clickableComponent != null && InputHelper.isMouseOverUIEntity(mouseX, mouseY, position, size)) {
                eventHandler.callEvent(new UIElementClickedEvent(entity));

                clickableComponent.getRunnable().run();
            }
        });

        mouseWasPressed = mousePressed;
    }
}
