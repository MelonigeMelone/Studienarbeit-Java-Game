package de.davidtobi.javagame.game.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.component.ui.*;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.UIEntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.system.ui.UIClickSystem;
import de.davidtobi.javagame.engine.ecs.system.ui.UIHoverSystem;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.codingtask.CodingTasks;
import de.davidtobi.javagame.game.textsequence.data.TextSequences;

import java.awt.*;
import java.util.List;

public class MainMenuScene extends Scene {

    public MainMenuScene() {
        super("MainMenuScene");

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        addRendererSystem(new UIEntityRendererSystem());
        addSystem(new UIClickSystem());
        addSystem(new UIHoverSystem());

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1920), dimensionHelper.getCenteredY(1080), 0),
                new UISizeComponent(1920, 1080),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/mainMenuBackground.png", Texture.class))
        )));

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(550), dimensionHelper.getCenteredY(150) - 325, 1),
                new UISizeComponent(550, 150),
                new UILabelComponent("JavaMind", new Color(0xFFBB00), new Font("Arial", Font.BOLD, 75),
                        HorizontalAlignment.CENTER, VerticalAlignment.CENTER)
        )));

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(550), dimensionHelper.getCenteredY(150) - 50, 1),
                new UISizeComponent(550, 150),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/button.png", Texture.class)),
                new UIHoverComponent(GameEngine.getResourceController().loadResource("/img/ui/button_hover.png", Texture.class)),
                new UILabelComponent("Spielen", new Color(0xFFBB00), new Font("Arial", Font.BOLD, 37),
                        HorizontalAlignment.CENTER, VerticalAlignment.CENTER),
                new UIClickableComponent(() -> {
                    TextSequences.reset();
                    GameEngine.getSceneController().switchScene(new CodingScene(CodingTasks.TEST_FUNCTIONALITY));
                })
        )));

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(550), dimensionHelper.getCenteredY(150) + 150, 1),
                new UISizeComponent(550, 150),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/button.png", Texture.class)),
                new UIHoverComponent(GameEngine.getResourceController().loadResource("/img/ui/button_hover.png", Texture.class)),
                new UILabelComponent("Spiel Verlassen", new Color(0xFFBB00), new Font("Arial", Font.BOLD, 37),
                        HorizontalAlignment.CENTER, VerticalAlignment.CENTER),
                new UIClickableComponent(() -> {
                    System.exit(0);
                })
        )));

        addEntity(new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(550) - 850, dimensionHelper.getCenteredY(150) + 500, 1),
                new UISizeComponent(550, 150),
                new UILabelComponent("Version: 0.1 Alpha",Color.WHITE, new Font("Arial", Font.BOLD, 20),
                        HorizontalAlignment.CENTER, VerticalAlignment.CENTER),
                new UIClickableComponent(() -> {
                    System.exit(0);
                })
        )));
    }

    @Override
    public void onEnter() {

    }
}
