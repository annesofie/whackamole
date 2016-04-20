package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.model.FileName;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.views.MainMenuRenderer;


/**
 * Created by AnneSofie on 04.04.2016.
 */
public class MainMenuScreen implements Screen {



    private MainMenuRenderer renderer;
    private Preferences prefs;
    final WhackAMole game;
    private int screenWidth, screenHeight, btnWidth, btnHeight;
    private Skin skin;
    private Stage stage;
    private Screen screen;
    private CheckBox soundCheckBox;

    public MainMenuScreen(final WhackAMole game) {
        this.game = game;
        this.screen = this;
        this.renderer = new MainMenuRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        btnWidth = 680*screenWidth/900;//(new Texture(FileName.CREATE_GAME_BTN.filename())).getWidth();
        btnHeight = 13*screenHeight/160;//(new Texture(FileName.CREATE_GAME_BTN.filename())).getHeight();

        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
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
        skin.add("mute", new Texture(FileName.MUTE.filename()));

        skin.add("createGameClicked", new Texture(FileName.CREATE_GAME_BTN_CLICKED.filename()));
        skin.add("joinGameClicked", new Texture(FileName.JOIN_GAME_BTN_CLICKED.filename()));
        skin.add("settingsClicked", new Texture(FileName.SETTINGS_BTN_CLICKED.filename()));
        skin.add("instructionsClicked", new Texture(FileName.INSTRUCTIONS_BTN_CLICKED.filename()));
        skin.add("unmute", new Texture(FileName.UNMUTE.filename()));

        soundCheckBox = new CheckBox("sound", new CheckBox.CheckBoxStyle(skin.getDrawable("unmute"), skin.getDrawable("mute"), new BitmapFont(), new Color()));
        if(this.prefs.getBoolean(Prefs.ISSOUND.key())) {
            soundCheckBox.setChecked(true);
        }
        else {
            soundCheckBox.setChecked(false);
        }
        soundCheckBox.setPosition(screenWidth/2-screenWidth*23/300, screenHeight/4);


        //Buttons:

        ImageButton createGameButton = new ImageButton(skin.getDrawable("createGameBtn"),skin.getDrawable("createGameClicked"));
        ImageButton joinGameButton = new ImageButton(skin.getDrawable("joinGameBtn"),skin.getDrawable("joinGameClicked"));
        //ImageButton settingsButton = new ImageButton(skin.getDrawable("settingsBtn"),skin.getDrawable("settingsClicked"));
        ImageButton instructionsButton = new ImageButton(skin.getDrawable("instructionsBtn"),skin.getDrawable("instructionsClicked"));



        createGameButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*9/12-btnHeight/2);
        joinGameButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*7/12-btnHeight/2);
        //settingsButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*5/12-btnHeight/2);
        instructionsButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*5/12-btnHeight/2);

        createGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToSettingsScreen(screen);
            }
        });

        soundCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("soundBtnClicked");
                if(soundCheckBox.isChecked()){
                    //soundCheckBox.setChecked(false);
                    prefs.putBoolean(Prefs.ISSOUND.key(), true);
                    prefs.flush();
                } else {
                    //soundCheckBox.setChecked(true);
                    prefs.putBoolean(Prefs.ISSOUND.key(), false);
                    prefs.flush();
                }
            }
        });

        joinGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToJoinGameScreen(screen);
            }
        });

//        settingsButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.goToSettingsScreen(screen);
//            }
//        });

        instructionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToInstructionsScreen(screen);
            }
        });

        //Stage:

        stage.addActor(createGameButton);
        stage.addActor(joinGameButton);
        //stage.addActor(settingsButton);
        stage.addActor(instructionsButton);
        for (Actor actor: stage.getActors()) {
            actor.setHeight(btnHeight);
            actor.setWidth(btnWidth);
        }
        stage.addActor(soundCheckBox);
        stage.getActors().get(3).setSize(screenWidth*46/300, screenHeight*128/1600);
        return stage;
    }

}