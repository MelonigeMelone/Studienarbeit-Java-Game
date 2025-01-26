package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.SizeComponent;
import de.davidtobi.javagame.engine.ecs.component.TextureComponent;
import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.RotationComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UITextureComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.EntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.system.MovementSystem;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;

import java.util.List;

public class ExampleScene extends Scene {

    private final Camera camera;
    private final Entity player;
    public ExampleScene() {
        super("exampleScene");

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        camera = new Camera(dimensionHelper.getCenteredX(0), dimensionHelper.getCenteredY(0
        ), GameEngine.getDimensionHelper().getCurrentDimension().width,
                GameEngine.getDimensionHelper().getCurrentDimension().height);

        addRendererSystem(new EntityRendererSystem(camera));


        player = new Entity("Player", List.of(
                new PositionComponent(0, 0, 1),
                new SizeComponent(70, 70),
                new TextureComponent(GameEngine.getResourceController().loadResource("/img/player.png", Texture.class)),
                new RotationComponent(0))
        );

        addEntity(player);

        addEntity(new Entity("TestObject", List.of(
                new PositionComponent(100, 100, 1),
                new SizeComponent(100, 100),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/default.png", Texture.class))
        )));

        addListener(new TestListener(player, camera));


    }

    @Override
    public void onEnter() {
    }
}
