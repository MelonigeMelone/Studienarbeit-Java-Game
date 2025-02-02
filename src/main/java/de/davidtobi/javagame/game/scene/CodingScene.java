package de.davidtobi.javagame.game.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.SyntaxHighlighter;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.component.ui.UICodingComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UIPositionComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UISizeComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.ecs.renderersystem.UIEntityRendererSystem;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;

import java.awt.*;
import java.util.List;

public class CodingScene extends Scene {

    public CodingScene() {
        super("scene_coding");

        DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

        addRendererSystem(new UIEntityRendererSystem());

        addEntity(new Entity("CodingBox", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(300), dimensionHelper.getCenteredY(300), 0),
                new UISizeComponent(300,300),
                new UICodingComponent(Color.WHITE, new Font("Arial", Font.PLAIN, 12),
                        HorizontalAlignment.LEFT, VerticalAlignment.TOP, SyntaxHighlighter.JAVA, () -> "boolean isTouching = false;\n"
        ))));
    }

    @Override
    public void onEnter() {

    }
}
