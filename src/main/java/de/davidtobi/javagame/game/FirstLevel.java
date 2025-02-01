package de.davidtobi.javagame.game;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.camera.Camera;
import de.davidtobi.javagame.engine.ecs.component.*;
import de.davidtobi.javagame.engine.ecs.component.ui.RotationComponent;
import de.davidtobi.javagame.engine.ecs.model.Component;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.EntityRendererSystem;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.world.Level;
import de.davidtobi.javagame.game.world.LevelBlock;
import de.davidtobi.javagame.game.world.LevelBlockType;

import java.util.ArrayList;
import java.util.List;

public class FirstLevel extends Scene {

    private final Camera camera;
    private final Entity player;

    public FirstLevel() {
        super("FirstLevel");

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        camera = new Camera(
                dimensionHelper.getCenteredX(300),
                dimensionHelper.getCenteredY(300),
                GameEngine.getDimensionHelper().getCurrentDimension().width,
                GameEngine.getDimensionHelper().getCurrentDimension().height
        );

        addRendererSystem(new EntityRendererSystem(camera));

        Level level = new Level(
                50,
                20,
                15,
                2,
                2,
                LevelBlockType.GRASS,
                new ArrayList<>() {{
                    for (int x = 0; x < 20; x++) {
                        add(new LevelBlock(x, 0, LevelBlockType.TREE, true));
                        add(new LevelBlock(x, 14, LevelBlockType.TREE, true));
                    }
                    for (int y = 1; y < 14; y++) {
                        add(new LevelBlock(0, y, LevelBlockType.TREE, true));
                        add(new LevelBlock(19, y, LevelBlockType.TREE, true));
                    }

                    for (int x = 2; x < 7; x++) {
                        add(new LevelBlock(x, 13, LevelBlockType.ROCK, false));
                    }
                    for (int y = 2; y < 13; y++) {
                        add(new LevelBlock(2, y, LevelBlockType.ROCK, false));
                    }
                    for(int y = 13; y > 8; y--){
                        add(new LevelBlock(7, y, LevelBlockType.ROCK, false));
                    }
                    for (int x = 8; x < 14; x++) {
                        add(new LevelBlock(x, 9, LevelBlockType.ROCK, false));
                    }
                    for(int y = 8; y > 5; y--){
                        add(new LevelBlock(13, y, LevelBlockType.ROCK, false));
                    }
                    for (int x = 13; x > 5; x--) {
                        add(new LevelBlock(x, 5, LevelBlockType.ROCK, false));
                    }
                    for(int y = 4; y > 0; y--){
                        add(new LevelBlock(6, y, LevelBlockType.ROCK, false));
                    }
                    for (int x = 6; x < 19; x++) {
                        add(new LevelBlock(x, 1, LevelBlockType.ROCK, false));
                    }
                    for (int y = 1; y < 7; y++) {
                        add(new LevelBlock(18, y, LevelBlockType.ROCK, false));
                    }
                    for (int y = 1; y < 13; y++) {
                        add(new LevelBlock(4, y, LevelBlockType.TREE, true));
                    }
                    for (int x = 5; x < 13; x++) {
                        add(new LevelBlock(x, 7, LevelBlockType.TREE, true));
                    }
                    for (int x = 8; x < 17; x++) {
                        add(new LevelBlock(x, 3, LevelBlockType.TREE, true));
                    }
                    for (int y = 4; y < 14; y++) {
                        add(new LevelBlock(16, y, LevelBlockType.TREE, true));
                    }
                    for (int x = 9; x < 16; x++) {
                        add(new LevelBlock(x, 11, LevelBlockType.TREE, true));
                    }
                    for (int y = 12; y < 14; y++) {
                        add(new LevelBlock(9, y, LevelBlockType.TREE, true));
                    }
                    add(new LevelBlock(18, 7, LevelBlockType.GATE, true));
                }}
        );

        player = new Entity("Player", List.of(
                new PositionComponent(level.getPlayerStartX() * level.getScale(), level.getPlayerStartY() * level.getScale(), 3),
                new SizeComponent(level.getScale(), level.getScale()),
                new TextureComponent(GameEngine.getResourceController().loadResource("/img/player.png", Texture.class)),
                new RotationComponent(0)
        ));

        addEntity(player);

        for (int i = 0; i < level.getSizeX(); i++) {
            for (int j = 0; j < level.getSizeY(); j++) {
                addEntity(new Entity("Grass_" + i + "_" + j, List.of(
                        new PositionComponent(i * level.getScale(), j * level.getScale(), 1),
                        new SizeComponent(level.getScale(), level.getScale()),
                        new TextureComponent(GameEngine.getResourceController().loadResource(level.getBackground().getResourcePath(), Texture.class))
                )));
            }
        }

        int decorationCounter = 0;
        for (LevelBlock decoration : level.getDecorations()) {
            List<Component> components = new ArrayList<>(List.of(
                    new PositionComponent(decoration.getX() * level.getScale(), decoration.getY() * level.getScale(), 2),
                    new SizeComponent(level.getScale(), level.getScale()),
                    new TextureComponent(GameEngine.getResourceController().loadResource(decoration.getLevelBlockType().getResourcePath(), Texture.class))
            ));

            if (decoration.isCollidable()) {
                components.add(new CollidableComponent());
            }

            addEntity(new Entity("Decoration_" + decorationCounter, components));
            decorationCounter++;
        }

        addListener(new TestListener(player, camera));
    }

    @Override
    public void onEnter() {
        camera.update(player);
    }
}