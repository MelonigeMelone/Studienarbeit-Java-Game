package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.ecs.component.PositionComponent;
import de.davidtobi.javagame.engine.ecs.component.SizeComponent;
import de.davidtobi.javagame.engine.ecs.component.TextureComponent;
import de.davidtobi.javagame.engine.ecs.component.VelocityComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UITextureComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.EntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.system.MovementSystem;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;

import java.util.List;

public class ExampleScene extends Scene {

    public ExampleScene() {
        super("exampleScene");

        addSystem(new MovementSystem());
        addRendererSystem(new EntityRendererSystem());

        Entity player = new Entity("Player", List.of(
                new PositionComponent(50, 50, 1),
                new SizeComponent(100, 100),
                new TextureComponent(GameEngine.getResourceController().loadResource("/img/default.png", Texture.class)),
                new VelocityComponent(0, 0, 0))
        );

        addEntity(player);

        addEntity(new Entity("TestObject", List.of(
                new PositionComponent(100, 100, 1),
                new SizeComponent(100, 100),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/default.png", Texture.class))
        )));

        addListener(new TestListener(player));


    }

    @Override
    public void onEnter() {

    }
}
