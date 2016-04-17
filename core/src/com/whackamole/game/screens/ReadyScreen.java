package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.ReadyController;
import com.whackamole.game.model.FileName;
import com.whackamole.game.views.ReadyRenderer;

/**
 * Created by Lars on 15/04/16.
 */
public class ReadyScreen implements Screen{


    private WhackAMole game;
    private Stage stage;
    private Skin skin;
    private ReadyRenderer renderer;
    private ReadyController controller;

    public ReadyScreen(final WhackAMole game) {

        this.game = game;
        this.renderer = new ReadyRenderer(game.getMatch());
        this.controller = new ReadyController(game.getMatch(), this);

        renderer.loadRenderer(loadActors());

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        controller.loadController();
    }

    @Override
    public void render(float delta) {
        renderer.render();
    }


    public Stage loadActors() {
        skin = new Skin();
        stage = new Stage();

        float canvasHeight = Gdx.graphics.getHeight();
        float canvasWidth = Gdx.graphics.getWidth();
        float btnXPos = ((float)1/4 * canvasWidth);
        float btnYPos = ((float)1/3 * canvasHeight);

        skin.add("btnNotClicked", new Texture(Gdx.files.internal(FileName.READYBTN.filename())));
        skin.add("btnClicked", new Texture(Gdx.files.internal(FileName.READYBTNCLICKED.filename())));

        ImageButton btn = new ImageButton(skin.getDrawable(("btnNotClicked")), skin.getDrawable("btnClicked"));
        btn.setName("readybtn");
        float btnWidth = btn.getWidth();
        btn.setPosition(canvasWidth/2-btnWidth/2, btnYPos);

        addClickListener(btn);


        stage.addActor(btn);

        return stage;
    }


    private void addClickListener(final ImageButton button) {

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.isReady();
            }
        });
    }


    public void goToGameScreen() {
        game.goToGameScreen(this);
    }




    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }



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
