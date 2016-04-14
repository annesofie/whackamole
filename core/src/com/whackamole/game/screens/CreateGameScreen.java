package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.CreateGameController;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.FileName;
import com.whackamole.game.views.CreateGameRenderer;

/**
 * Created by Lars on 12/04/16.
 */
public class CreateGameScreen implements Screen{




    private final WhackAMole game;
    private CreateGame createGame;
    private CreateGameController controller;
    private CreateGameRenderer renderer;

    Stage stage;
    Skin skin;



    public CreateGameScreen(final WhackAMole game) {

        this.game = game;

        this.createGame = new CreateGame();

        this.renderer = new CreateGameRenderer(createGame);

        this.controller = new CreateGameController(createGame);



    }


    @Override
    public void show() {

        createGame.loadCreateGame();
        renderer.loadRenderer(loadActors());

    }


    @Override
    public void render(float delta) {
        this.renderer.render();
    }




    private Stage loadActors() {

        /*
            Loads and returns the actors that can be acted upon on the screen
         */

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        float canvasHeight = Gdx.graphics.getHeight();
        float canvasWidth = Gdx.graphics.getWidth();

        skin.add("btnNotClicked", new Texture(Gdx.files.internal(FileName.CREATEGAMEBTN.filename())));
        skin.add("btnClicked", new Texture(Gdx.files.internal(FileName.CREATEGAMEBTNCLICKED.filename())));
        skin.add("background", new Texture(FileName.BACKGROUND_GRASS.filename()));

        Image background = new Image(skin.getDrawable("background"));
        ImageButton btn = new ImageButton(skin.getDrawable(("btnNotClicked")), skin.getDrawable("btnClicked"));
        TextField textField = new TextField("", skin);
        textField.setMessageText("Enter game name");


        float btnXPos = ((float)1/4 * canvasWidth);
        float btnYPos = ((float)1/3 * canvasHeight);

        btn.setPosition(btnXPos, btnYPos);
        textField.setPosition(btnXPos, btnYPos + (float)(1/4) * canvasHeight);

        stage.addActor(btn);
        stage.addActor(textField);
        stage.addActor(background);

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToGameScreen();
                dispose();
            }
        });

        Gdx.input.setInputProcessor(stage);

        return stage;
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
