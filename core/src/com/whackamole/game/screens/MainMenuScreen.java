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
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.utils.StageExtension;
import com.whackamole.game.views.Assets;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.views.MainMenuRenderer;


public class MainMenuScreen implements Screen {

    private MainMenuRenderer renderer;
    private final ScreenController screenController;
    private int screenWidth, screenHeight;
    private Skin skin;
    private StageExtension stage;
    private Preferences prefs;
    private CheckBox soundCheckBox;


    public MainMenuScreen(final ScreenController screenController) {
        this.screenController = screenController;
        this.renderer = new MainMenuRenderer();
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        this.stage = StageExtension.getCleanInstance();
        this.skin = new Skin();
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

        renderer.loadRenderer(loadActors());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        renderer.render();
    }


    private StageExtension loadActors(){
        float btnWidth = 680*screenWidth/900;//(new Texture(FileName.CREATE_GAME_BTN.filename())).getWidth();
        float btnHeight = 13*screenHeight/160;//(new Texture(FileName.CREATE_GAME_BTN.filename())).getHeight();

        skin.add("createGameBtn", Assets.manager.get(Assets.CREATE_GAME_BTN, Texture.class));
        skin.add("joinGameBtn", Assets.manager.get(Assets.JOIN_GAME_BTN, Texture.class));
        skin.add("settingsBtn", Assets.manager.get(Assets.SETTINGS_BTN, Texture.class));
        skin.add("instructionsBtn", Assets.manager.get(Assets.INSTRUCTIONS_BTN, Texture.class));
        skin.add("createGameClicked", Assets.manager.get(Assets.CREATE_GAME_BTN_CLICKED, Texture.class));
        skin.add("joinGameClicked", Assets.manager.get(Assets.JOIN_GAME_BTN_CLICKED, Texture.class));
        skin.add("settingsClicked", Assets.manager.get(Assets.SETTINGS_BTN_CLICKED, Texture.class));
        skin.add("instructionsClicked", Assets.manager.get(Assets.INSTRUCTIONS_BTN_CLICKED, Texture.class));
        skin.add("mute", Assets.manager.get(Assets.MUTE_BTN, Texture.class));
        skin.add("unmute", Assets.manager.get(Assets.UNMUTE_BTN, Texture.class));

        soundCheckBox = new CheckBox("sound", new CheckBox.CheckBoxStyle(skin.getDrawable("unmute"), skin.getDrawable("mute"), new BitmapFont(), new Color()));
        if(this.prefs.getBoolean(Prefs.ISSOUND.key())) {
            soundCheckBox.setChecked(true);
        }
        else {
            soundCheckBox.setChecked(false);
        }
        soundCheckBox.setPosition(screenWidth/2-screenWidth*23/300, screenHeight/5);

        //Buttons:
        ImageButton createGameButton = new ImageButton(skin.getDrawable("createGameBtn"),skin.getDrawable("createGameClicked"));
        ImageButton joinGameButton = new ImageButton(skin.getDrawable("joinGameBtn"),skin.getDrawable("joinGameClicked"));
        ImageButton instructionsButton = new ImageButton(skin.getDrawable("instructionsBtn"),skin.getDrawable("instructionsClicked"));


        createGameButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*9/12-btnHeight);
        joinGameButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*7/12-btnHeight);
        //settingsButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*5/12-btnHeight/2);
        instructionsButton.setPosition(screenWidth/2-btnWidth/2,screenHeight*5/12-btnHeight);

        createGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToSettingsScreen();
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
                screenController.goToJoinGameScreen();
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
                screenController.goToInstructionsScreen();
            }
        });

        // Adding actors to the stage for display on screen
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

    @Override
    public void hide() {
        //dispose();
    }

    @Override
    public void dispose() {
        skin.dispose();
    }


    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
}