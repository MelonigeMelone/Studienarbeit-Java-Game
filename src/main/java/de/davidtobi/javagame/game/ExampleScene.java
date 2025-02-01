package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.ecs.component.*;
import de.davidtobi.javagame.engine.ecs.component.ui.RotationComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.EntityRendererSystem;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.world.Level;
import de.davidtobi.javagame.game.world.LevelBlockType;

import java.util.List;

public class ExampleScene extends Scene {

    private final Camera camera;
    //private final Entity player;
    public ExampleScene() {
        super("exampleScene");

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        camera = new Camera(
                dimensionHelper.getCenteredX(300),
                dimensionHelper.getCenteredY(300),
                GameEngine.getDimensionHelper().getCurrentDimension().width,
                GameEngine.getDimensionHelper().getCurrentDimension().height);

        addRendererSystem(new EntityRendererSystem(camera));



        /*Level level = new Level(25, 25, 3, 3, LevelBlockType.GRASS, new LevelBlockType[][] {
                {LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE, LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
                {LevelBlockType.TREE},
        });


        player = new Entity("Player", List.of(
                new PositionComponent(level.getPlayerStartX() * 100, level.getPlayerStartY() * 100, 3),
                new SizeComponent(70, 70),
                new TextureComponent(GameEngine.getResourceController().loadResource("/img/player.png", Texture.class)),
                new RotationComponent(0))
        );

        addEntity(player);

        for(int i = 0; i<level.getSizeX(); i++) {
            for(int j = 0; j<level.getSizeY(); j++) {
                addEntity(new Entity("Grass_" + i + j, List.of(
                        new PositionComponent(100 + i * 100, 100 + j * 100, 2),
                        new SizeComponent(100, 100),
                        new TextureComponent(GameEngine.getResourceController().loadResource(level.getBackground().getResourcePath(), Texture.class))
                )));
            }
        }

        for(int i = 0; i< level.getDecorations().length; i++) {
            for(int j = 0; j< level.getDecorations()[i].length; j++) {

                addEntity(new Entity("TestObject" + i + j, List.of(
                        new PositionComponent(100 + i * 100, 100 + j * 100, 3),
                        new SizeComponent(100, 100),
                        new TextureComponent(GameEngine.getResourceController().loadResource(level.getDecorations()[i][j].getResourcePath(), Texture.class)),
                        new CollidableComponent()
                )));
            }
        }



        addListener(new TestListener(player, camera));*/
    }

    @Override
    public void onEnter() {
       // camera.update(player);
    }
}
