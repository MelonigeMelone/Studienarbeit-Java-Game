package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.component.ui.*;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.UIEntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.system.ui.UIClickSystem;
import de.davidtobi.javagame.engine.event.event.input.KeyReleasedEvent;
import de.davidtobi.javagame.engine.event.model.EventHandler;
import de.davidtobi.javagame.engine.event.model.Listener;
import de.davidtobi.javagame.engine.log.EngineLogger;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;

import java.awt.*;
import java.util.List;

public class TextComponentScene extends Scene implements Listener {

    private String textFieldValue = "Test";
    private Entity textComponent;

    public TextComponentScene() {
        super("textcompoent_scene");


        addRendererSystem(new UIEntityRendererSystem());
        addSystem(new UIClickSystem());

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        UITextBoxComponent textBoxComponent = new UITextBoxComponent("");
        textComponent = new Entity("TextComponet", List.of(
                new UIPositionComponent( dimensionHelper.getCenteredX(100),  dimensionHelper.getCenteredY(100), 1),
                new UISizeComponent(100, 100),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/default.png", Texture.class)),
                new UILabelComponent("", Color.ORANGE, new Font("Arial", Font.PLAIN, 20),
                        HorizontalAlignment.CENTER, VerticalAlignment.CENTER, () -> String.valueOf(textFieldValue)),
                textBoxComponent,
                new UIClickableComponent(textBoxComponent::toggleFocus)
        ));

        addEntity(textComponent);

        addListener(this);
    }

    @Override
    public void onEnter() {

    }

    @EventHandler
    public void onTextInput(KeyReleasedEvent event) {
        UITextBoxComponent textBoxComponent = textComponent.getComponent(UITextBoxComponent.class);
        if(!textBoxComponent.isFocused()) {
            return;
        }

        if(event.getKeyCode() == 8) {
            if(textFieldValue.length() > 0) {
                textFieldValue = textFieldValue.substring(0, textFieldValue.length() - 1);
            }
        } else {
            textFieldValue += event.getKeyChar();
            EngineLogger.log(EngineLoggerLevel.DEBUG, textFieldValue);
        }
    }
}
