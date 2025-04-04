package de.davidtobi.javagame.engine.ecs.system.ui;

import de.davidtobi.javagame.engine.ecs.component.ui.UIHoverComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UIPositionComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UISizeComponent;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.System;
import de.davidtobi.javagame.engine.event.EventHandler;
import de.davidtobi.javagame.engine.input.InputHandler;
import de.davidtobi.javagame.engine.math.model.Vector2D;
import de.davidtobi.javagame.engine.util.InputHelper;

public class UIHoverSystem extends System {

    @Override
    public Class<? extends Component>[] getRequiredComponents() {
        return new Class[] {UIPositionComponent.class, UISizeComponent.class, UIHoverComponent.class};
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

        float mouseX = mousePosition.getX();
        float mouseY = mousePosition.getY();

        getEnabledEntities().forEach(entity -> {
            UIPositionComponent position = entity.getComponent(UIPositionComponent.class);
            UISizeComponent size = entity.getComponent(UISizeComponent.class);
            UIHoverComponent hoverComponent = entity.getComponent(UIHoverComponent.class);

            if(position != null && size != null && hoverComponent != null) {
                hoverComponent.setHovered(InputHelper.isMouseOverUIEntity(mouseX, mouseY, position, size));
            }
        });
    }
}
