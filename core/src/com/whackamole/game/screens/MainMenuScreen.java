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
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.views.Assets;
import com.whackamole.game.views.MainMenuRenderer;


/**
 * Created by AnneSofie on 04.04.2016.
 */
public class MainMenuScreen implements Screen {


    private MainMenuRenderer renderer;
    private final ScreenController screenController;
    private int screenWidth, screenHeight;
    private Skin skin;
    private Stage stage;

    public MainMenuScreen(final ScreenController screenController) {
        this.screenController = screenController;
        this.renderer = new MainMenuRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

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


    private Stage loadActors(){
        stage = new Stage();
        skin = new Skin();

        skin.add("createGameBtn", Assets.manager.get(Assets.CREATE_GAME_BTN, Texture.class));
        skin.add("joinGameBtn", Assets.manager.get(Assets.JOIN_GAME_BTN, Texture.class));
        skin.add("settingsBtn", Assets.manager.get(Assets.SETTINGS_BTN, Texture.class));
        skin.add("instructionsBtn", Assets.manager.get(Assets.INSTRUCTIONS_BTN, Texture.class));

        skin.add("createGameClicked", Assets.manager.get(Assets.CREATE_GAME_BTN_CLICKED, Texture.class));
        skin.add("joinGameClicked", Assets.manager.get(Assets.JOIN_GAME_BTN_CLICKED, Texture.class));
        skin.add("settingsClicked", Assets.manager.get(Assets.SETTINGS_BTN_CLICKED, Texture.class));
        skin.add("instructionsClicked", Assets.manager.get(Assets.INSTRUCTIONS_BTN_CLICKED, Texture.class));

        float btnWidth = Assets.manager.get(Assets.CREATE_GAME_BTN, Texture.class).getWidth();
        float btnHeight = Assets.manager.get(Assets.CREATE_GAME_BTN, Texture.class).getHeight();

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
                screenController.goToCreateGameScreen();
            }
        });

        joinGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToJoinGameScreen();
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToSettingsScreen();
            }
        });

        instructionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToInstructionsScreen();
            }
        });

        // Adding actors to the stage for display on screen
        stage.addActor(createGameButton);
        stage.addActor(joinGameButton);
        stage.addActor(settingsButton);
        stage.addActor(instructionsButton);

        return stage;
    }

    @Override
    public void hide() {
        dispose();
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

}