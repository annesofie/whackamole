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
import com.whackamole.game.views.MainMenuRenderer;


/**
 * Created by AnneSofie on 04.04.2016.
 */
public class MainMenuScreen implements Screen {



    private MainMenuRenderer renderer;
    final WhackAMole game;
    private int screenWidth, screenHeight, btnWidth, btnHeight;
    private Skin skin;
    private Stage stage;
    //private Screen screen;

    public MainMenuScreen(final WhackAMole game) {
        this.game = game;
        //this.screen = this;
        this.renderer = new MainMenuRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        btnWidth = (new Texture(FileName.CREATE_GAME_BTN.filename())).getWidth();
        btnHeight = (new Texture(FileName.CREATE_GAME_BTN.filename())).getHeight();

        renderer.loadRenderer(loadActors());
    }

    @Override
    public void show() {

        //Gdx.graphics.setContinuousRendering(false);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        renderer.render();
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

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }

    private Stage loadActors(){
        stage = new Stage();

        //skin:

        skin = new Skin();

        skin.add("createGameBtn", new Texture(FileName.CREATE_GAME_BTN.filename()));
        skin.add("joinGameBtn", new Texture(FileName.JOIN_GAME_BTN.filename()));
        skin.add("settingsBtn", new Texture(FileName.SETTINGS_BTN.filename()));
        skin.add("instructionsBtn", new Texture(FileName.INSTRUCTIONS_BTN.filename()));

        skin.add("createGameClicked", new Texture(FileName.CREATE_GAME_BTN_CLICKED.filename()));
        skin.add("joinGameClicked", new Texture(FileName.JOIN_GAME_BTN_CLICKED.filename()));
        skin.add("settingsClicked", new Texture(FileName.SETTINGS_BTN_CLICKED.filename()));
        skin.add("instructionsClicked", new Texture(FileName.INSTRUCTIONS_BTN_CLICKED.filename()));

        //Buttons:

        ImageButton createGameButton = new ImageButton(skin.getDrawable("createGameBtn"),skin.getDrawable("createGameClicked"));
        ImageButton joinGameButton = new ImageButton(skin.getDrawable("joinGameBtn"),skin.getDrawable("joinGameClicked"));
        ImageButton settingsButton = new ImageButton(skin.getDrawable("settingsBtn"),skin.getDrawable("settingsClicked"));
        ImageButton instructionsButton = new ImageButton(skin.getDrawable("instructionsBtn"),skin.getDrawable("instructionsClicked"));

        createGameButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*9/12-btnHeight/2);
        joinGameButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*7/12-btnHeight/2);
        settingsButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*5/12-btnHeight/2);
        instructionsButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*3/12-btnHeight/2);

        createGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToCreateGameScreen();
            }
        });

        joinGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToJoinGameScreen();
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToSettingsScreen();
            }
        });

        instructionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToInstructionsScreen();
            }
        });

        //Stage:

        stage.addActor(createGameButton);
        stage.addActor(joinGameButton);
        stage.addActor(settingsButton);
        stage.addActor(instructionsButton);

        return stage;
    }

}