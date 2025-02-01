package de.davidtobi.javagame.game.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.ecs.component.*;
import de.davidtobi.javagame.engine.ecs.component.ui.RotationComponent;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.EntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.system.MovementSystem;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.listener.EntityCollisionListener;
import de.davidtobi.javagame.game.listener.PlayerMovementListener;
import de.davidtobi.javagame.game.world.Level;
import de.davidtobi.javagame.game.world.LevelBlock;

import java.util.ArrayList;
import java.util.List;

public class LevelScene extends Scene {

    private final Camera camera;
    private final Entity player;

    public LevelScene(Level level) {
        super("scene_level");

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        camera = new Camera(dimensionHelper.getCenteredX(300), dimensionHelper.getCenteredY(300
        ), GameEngine.getDimensionHelper().getCurrentDimension().width,
                GameEngine.getDimensionHelper().getCurrentDimension().height);

        addRendererSystem(new EntityRendererSystem(camera));

        addSystem(new MovementSystem());

        player = new Entity("Player", List.of(
                new PositionComponent(level.getPlayerStartX() * level.getScale(), level.getPlayerStartY() * level.getScale(), 3),
                new SizeComponent(level.getScale(), level.getScale()),
                new TextureComponent(GameEngine.getResourceController().loadResource("/img/player.png", Texture.class)),
                new RotationComponent(0),
                new VelocityComponent(0,0,0),
                new CollidableComponent())
        );

        addEntity(player);

        for(int i = 0; i<level.getSizeX(); i++) {
            for(int j = 0; j<level.getSizeY(); j++) {
                addEntity(new Entity("Level_Background_" + i + j, List.of(
                        new PositionComponent(level.getScale() + i * level.getScale(), level.getScale() + j * level.getScale(), 2),
                        new SizeComponent(level.getScale(), level.getScale()),
                        new TextureComponent(GameEngine.getResourceController().loadResource(level.getBackground().getResourcePath(), Texture.class))
                )));
            }
        }

        int decorationCounter = 0;
        for(LevelBlock levelBlock : level.getDecorations()) {
            List<Component> levelBlockComponents = new ArrayList<>(List.of(
                    new PositionComponent(level.getScale() * levelBlock.getX(), level.getScale() * levelBlock.getY(), 2),
                    new SizeComponent(level.getScale(), level.getScale()),
                    new TextureComponent(GameEngine.getResourceController().loadResource(levelBlock.getLevelBlockType().getResourcePath(), Texture.class)
                    )));

             if(levelBlock.isCollidable()) {
                levelBlockComponents.add(new CollidableComponent());
            }

            addEntity(new Entity("Level_Decoration" + decorationCounter, levelBlockComponents));
            decorationCounter++;
        }

        addListener(new PlayerMovementListener(camera, player));
        addListener(new EntityCollisionListener());
    }

    @Override
    public void onEnter() {
        camera.update(player);
    }
}
