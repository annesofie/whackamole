package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.Match;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.StageExtension;
import com.whackamole.game.views.Assets;
import com.whackamole.game.views.GameOverRenderer;


public class GameOverScreen implements Screen {


    private final ScreenController screenController;
    private GameOverRenderer renderer;
    private StageExtension stage;
    private Skin skin;
    private float screenHeight;
    private float screenWidth;

    public GameOverScreen(ScreenController screenController) {
        this.screenController = screenController;
        renderer = new GameOverRenderer();
        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();
        this.stage = StageExtension.getCleanInstance();

        renderer.loadRenderer(loadActors());
    }

    @Override
    public void show() {
        Match.getCurrentMatch().setIsOnGoingMatch(false);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        renderer.render();
    }


    private StageExtension loadActors(){
        skin = new Skin();

        skin.add("returnBtn", Assets.manager.get(Assets.LARGE_BACK_BTN, Texture.class));
        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));

        returnButton.getCells().get(0).size(screenWidth* Constants.returnButtonWidthRatio, screenHeight*Constants.returnButtonHeightRatio);

        returnButton.setPosition(returnButton.getWidth(), screenHeight - returnButton.getHeight()*2);

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToMainMenuScreen();
            }
        });

        stage.addActor(returnButton);

        return stage;
    }

    @Override public void hide() {
        // dispose();
    }

    @Override
    public void dispose() {
        skin.dispose();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}

}