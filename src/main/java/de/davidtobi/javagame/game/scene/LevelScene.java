package de.davidtobi.javagame.game.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.ecs.component.*;
import de.davidtobi.javagame.engine.ecs.component.ui.*;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.EntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.renderersystem.UIEntityRendererSystem;
import de.davidtobi.javagame.engine.ecs.system.FollowEntitySystem;
import de.davidtobi.javagame.engine.ecs.system.MovementSystem;
import de.davidtobi.javagame.engine.math.model.Vector2D;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.listener.CollisionListener;
import de.davidtobi.javagame.game.textsequence.data.TextSequences;
import de.davidtobi.javagame.game.listener.EntityCollisionListener;
import de.davidtobi.javagame.game.listener.InteractListener;
import de.davidtobi.javagame.game.listener.PlayerMovementListener;
import de.davidtobi.javagame.game.textsequence.model.TextSequence;
import de.davidtobi.javagame.game.world.Level;
import de.davidtobi.javagame.game.world.LevelBlock;
import de.davidtobi.javagame.game.world.data.LevelData;

import java.util.ArrayList;
import java.util.List;

public class LevelScene extends BaseGameScene {

    private final LevelData levelData;

    private final Camera camera;
    private final Entity player, roboter;

    private Entity collidingEntity;

    public LevelScene(LevelData levelData) {
        super("scene_level");

        this.levelData = levelData;

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        camera = new Camera(dimensionHelper.getCenteredX(300), dimensionHelper.getCenteredY(300
        ), GameEngine.getDimensionHelper().getCurrentDimension().width,
                GameEngine.getDimensionHelper().getCurrentDimension().height);

        addRendererSystem(new EntityRendererSystem(camera));
        addRendererSystem(new UIEntityRendererSystem());
        addSystem(new FollowEntitySystem());

        addSystem(new MovementSystem());

        int playerSize = 25 * levelData.getLevel().getScale();

        player = new Entity("Player", List.of(
                new PositionComponent(levelData.getLevel().getPlayerStartX() * levelData.getLevel().getScale(), levelData.getLevel().getPlayerStartY() * levelData.getLevel().getScale(), 3),
                new SizeComponent(playerSize, playerSize),
                //new TextureComponent(GameEngine.getResourceController().loadResource("/img/SpriteSheet.png", Texture.class)),
                new DynamicSpriteSheetComponent(GameEngine.getResourceController().loadResource("/img/SpriteSheet.png", Texture.class), 64, 64),
                new RotationComponent(0),
                new VelocityComponent(0,0,0),
                new CollidableComponent())
        );

        int roboterSize = 16 * levelData.getLevel().getScale();

        roboter = new Entity("Roboter", List.of(
                new PositionComponent(levelData.getLevel().getPlayerStartX() * levelData.getLevel().getScale() + 15, levelData.getLevel().getPlayerStartY() * levelData.getLevel().getScale() + 15, 3),
                new SizeComponent(roboterSize, roboterSize),
                new DynamicSpriteSheetComponent(GameEngine.getResourceController().loadResource("/img/roboterSpriteSheet.png", Texture.class), 32, 32),
                new RotationComponent(0),
                new FollowEntityComponent(player, new Vector2D(15, 15)))
        );

        addEntity(roboter);
        addEntity(player);

        int backgroundWidth = levelData.getLevel().getBackground().getWidth() * levelData.getLevel().getScale();
        int backgroundHeight = levelData.getLevel().getBackground().getHeight() * levelData.getLevel().getScale();

        for(int i = 0; i<levelData.getLevel().getSizeX(); i++) {
            for(int j = 0; j<levelData.getLevel().getSizeY(); j++) {
                addEntity(new Entity("Level_Background_" + i + j, List.of(
                        new PositionComponent(backgroundWidth + i * backgroundWidth, backgroundHeight + j * backgroundHeight, 2),
                        new SizeComponent(backgroundWidth, backgroundHeight),
                        new TextureComponent(new Texture(levelData.getLevel().getBackground().getSprite()))
                )));
            }
        }

        int decorationCounter = 0;
        for(LevelBlock levelBlock : levelData.getLevel().getDecorations()) {

            int height = levelBlock.getLevelBlockData().getHeight() * levelData.getLevel().getScale();
            int width = levelBlock.getLevelBlockData().getWidth() * levelData.getLevel().getScale();

            List<Component> levelBlockComponents = new ArrayList<>(List.of(
                    new PositionComponent(levelData.getLevel().getScale() * levelBlock.getX(), levelData.getLevel().getScale() * levelBlock.getY(), 2),
                    new SizeComponent(width, height),
                    new TextureComponent(new Texture(levelBlock.getLevelBlockData().getSprite()))));

            if(levelBlock.isCollidable()) {
                levelBlockComponents.add(new CollidableComponent());
            }

            Entity block = new Entity("Level_Decoration" + decorationCounter, levelBlockComponents);
            addEntity(block);

            if(levelBlock.isInteractable()) {
                levelBlock.setEntity(block);
            }

            decorationCounter++;
        }



        openTextSequence(new TextSequence(TextSequences.INTRO));

        addListener(new PlayerMovementListener(camera, player, this));
        addListener(new EntityCollisionListener(this));
        addListener(new InteractListener(this));
        addListener(new CollisionListener(this));
    }

    @Override
    public void onEnter() {
        camera.update(player);
    }

    public TextSequence getCurrentTextSequence() {
        return currentTextSequence;
    }

    public Entity getPlayer() {
        return player;
    }

    public Entity getRoboter() {
        return roboter;
    }

    public Entity getCollidingEntity() {
        return collidingEntity;
    }

    public void setCollidingEntity(Entity collidingEntity) {
        this.collidingEntity = collidingEntity;
    }

    public Level getLevel() {
        return levelData.getLevel();
    }
}
