package de.davidtobi.javagame.game.scene;

import de.davidtobi.javagame.engine.GameEngine;
import de.davidtobi.javagame.engine.data.HorizontalAlignment;
import de.davidtobi.javagame.engine.data.VerticalAlignment;
import de.davidtobi.javagame.engine.ecs.component.ui.UILabelComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UIPositionComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UISizeComponent;
import de.davidtobi.javagame.engine.ecs.component.ui.UITextureComponent;
import de.davidtobi.javagame.engine.ecs.model.Entity;
import de.davidtobi.javagame.engine.resource.model.Texture;
import de.davidtobi.javagame.engine.scene.Scene;
import de.davidtobi.javagame.engine.util.DimensionHelper;
import de.davidtobi.javagame.game.textsequence.listener.TextSequenceListener;
import de.davidtobi.javagame.game.textsequence.model.TextSequence;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;

public abstract class BaseGameScene extends Scene {

    protected final DimensionHelper dimensionHelper = GameEngine.getDimensionHelper();

    protected Entity sequenceTextbox, sequenceContent, sequenceNarrator, sequenceNarratorImage;
    protected boolean inTextSequence = false;
    protected TextSequence currentTextSequence;

    public BaseGameScene(String name) {
        super(name);

        sequenceTextbox = new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1440), dimensionHelper.getCenteredY(810) + 450, 20),
                new UISizeComponent(1440, 810),
                new UITextureComponent(GameEngine.getResourceController().loadResource("/img/ui/textbox.png", Texture.class))
        ));
        sequenceTextbox.setDisabled(true);
        addEntity(sequenceTextbox);

        sequenceContent = new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(850) + 175, dimensionHelper.getCenteredY(608) + 685, 21),
                new UISizeComponent(850, 608),
                new UILabelComponent("", Color.WHITE, new Font("Arial", Font.PLAIN, 30), HorizontalAlignment.LEFT, VerticalAlignment.TOP, new Supplier<String>() {
                    @Override
                    public String get() {
                        if (currentTextSequence == null) {
                            return "";
                        }
                        currentTextSequence.getCurrentMessage().tickCurrentDisplayedMessage();
                        return currentTextSequence.getCurrentMessage().getCurrentDisplayedMessage();
                    }
                })
        ));
        sequenceContent.setDisabled(true);
        addEntity(sequenceContent);

        sequenceNarrator = new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(1080) + 250, dimensionHelper.getCenteredY(608) + 600, 21),
                new UISizeComponent(1080, 608),
                new UILabelComponent("", Color.WHITE, new Font("Arial", Font.PLAIN, 45), HorizontalAlignment.LEFT, VerticalAlignment.TOP, new Supplier<String>() {
                    @Override
                    public String get() {
                        if (currentTextSequence == null) {
                            return "";
                        }
                        return currentTextSequence.getCurrentMessage().getNarrator().getName();
                    }
                })
        ));
        sequenceNarrator.setDisabled(true);
        addEntity(sequenceNarrator);

        sequenceNarratorImage = new Entity("UI", List.of(
                new UIPositionComponent(dimensionHelper.getCenteredX(125) - 475 , dimensionHelper.getCenteredY(125) + 415, 21),
                new UISizeComponent(150, 150),
                new UITextureComponent(null)
        ));
        sequenceNarratorImage.setDisabled(true);
        addEntity(sequenceNarratorImage);

        addListener(new TextSequenceListener(this));
    }


    public void openTextSequence(TextSequence textSequence) {
        this.inTextSequence = true;
        this.currentTextSequence = textSequence;

        sequenceTextbox.setDisabled(false);
        sequenceContent.setDisabled(false);
        sequenceNarrator.setDisabled(false);
        sequenceNarratorImage.setDisabled(false);
        sequenceNarratorImage.getComponent(UITextureComponent.class).setTexture(
                GameEngine.getResourceController().loadResource(textSequence.getCurrentMessage().getNarrator().getTexturePath(), Texture.class));
    }

    public void closeTextSequence() {
        this.inTextSequence = false;
        this.currentTextSequence = null;

        sequenceTextbox.setDisabled(true);
        sequenceContent.setDisabled(true);
        sequenceNarrator.setDisabled(true);
        sequenceNarratorImage.setDisabled(true);
    }

    public boolean isInTextSequence() {
        return inTextSequence;
    }

    public TextSequence getCurrentTextSequence() {
        return currentTextSequence;
    }
}
