package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.SizeComponent;
import de.davidtobi.javagame.engine.ecs.component.TextureComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.*;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.EntityRendererSystem;
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


public class MainMenuScene extends Scene implements Listener {

    private String playerName = "Enter your username";
    private Entity textComponent;

    public MainMenuScene() {
        super("MainMenu");

        addRendererSystem(new UIEntityRendererSystem());
        addSystem(new UIClickSystem());

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        UITextBoxComponent textBoxComponent = new UITextBoxComponent("");
        textComponent = new Entity("Player Name", List.of(
                new UIPositionComponent( dimensionHelper.getCenteredX(100),  dimensionHelper.getCenteredY(100), 1),
                new UISizeComponent(100, 100),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/gate.png", Texture.class)),
                new UILabelComponent("", Color.ORANGE, new Font("Arial", Font.PLAIN, 20),
                        HorizontalAlignment.CENTER, VerticalAlignment.CENTER, () -> String.valueOf(playerName)),
                textBoxComponent,
                new UIClickableComponent(textBoxComponent::toggleFocus)
        ));
        addEntity(textComponent);

        //Button startButton = new Button("Start Game");
        //GameEngine.getSceneController().switchScene(new FirstLevel());
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
            if(playerName.length() > 0) {
                playerName = playerName.substring(0, playerName.length() - 1);
            }
        } else {
            playerName += event.getKeyChar();
            EngineLogger.log(EngineLoggerLevel.DEBUG, playerName);
        }
    }
}
