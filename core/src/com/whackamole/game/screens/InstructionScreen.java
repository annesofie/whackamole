package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.views.Assets;
import com.whackamole.game.views.InstructionRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class InstructionScreen implements Screen {


    private final WhackAMole game;
    private InstructionRenderer renderer;
    private Skin skin;
    private Stage stage;
    private int screenWidth, screenHeight;
    private Screen screen;


    public InstructionScreen(final WhackAMole game, final SpriteBatch batch) {
        this.game = game;
        this.screen = this;
        renderer = new InstructionRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

    }

    @Override
    public void show() {
        renderer.loadRenderer(loadActors());
    }

    @Override
    public void render(float delta) {
        this.renderer.render();
    }

    private Stage loadActors(){
        stage = new Stage();
        skin = new Skin();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();

        Texture returnBtn = Assets.manager.get(Assets.RETURN_BTN, Texture.class);
        float returnBtnWidth = returnBtn.getWidth();
        float returnBtnHeight = returnBtn.getHeight();

        skin.add("returnBtn", returnBtn);
        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));

        returnButton.setPosition(screenWidth*9/10 - returnBtnWidth*2, screenHeight*8/10 - returnBtnHeight*2);

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToMainMenuScreen(screen);
            }
        });

        stage.addActor(returnButton);
        Gdx.input.setInputProcessor(stage);
        return stage;
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }


    // THE REST OF THE SCREEN METHODS
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void hide() {

    }
}