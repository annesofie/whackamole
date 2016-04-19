package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.model.FileName;
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
    private int returnBtnWidth, returnBtnHeight;
    //private Screen screen;


    public InstructionScreen(final WhackAMole game) {
        this.game = game;
        //this.screen = this;
        renderer = new InstructionRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        returnBtnWidth = (new Texture(FileName.RETURN_BTN.filename())).getWidth();
        returnBtnHeight = (new Texture(FileName.RETURN_BTN.filename())).getHeight();
    }

    @Override
    public void show() {
        renderer.loadRenderer(loadActors());
    }

    @Override
    public void render(float delta) {
        renderer.render();
    }

    private Stage loadActors(){
        stage = new Stage();
        skin = new Skin();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();

        skin.add("returnBtn", new Texture(FileName.RETURN_BTN.filename()));
        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));

        returnButton.setPosition(screenWidth*9/10 - returnBtnWidth*2, screenHeight*8/10 - returnBtnHeight*2);

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToMainMenuScreen();
            }
        });

        stage.addActor(returnButton);
        Gdx.input.setInputProcessor(stage);
        return stage;
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

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

}