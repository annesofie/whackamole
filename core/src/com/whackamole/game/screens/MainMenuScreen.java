package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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

    public MainMenuScreen(final WhackAMole game) {
        this.game = game;
        this.renderer = new MainMenuRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        btnWidth = (new Texture("CreateGameBtn.png")).getWidth();
        btnHeight = (new Texture("CreateGameBtn.png")).getHeight();
    }

    @Override
    public void show() {
        renderer.loadRenderer(loadActors());
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

        skin.add("createGameBtn", new Texture("CreateGameBtn.png"));
        skin.add("joinGameBtn", new Texture("JoinGameBtn.png"));
        skin.add("settingsBtn", new Texture(Gdx.files.internal(FileName.SETTINGSBTN.filename())));
        skin.add("instructionsBtn", new Texture("InstructionsBtn.png"));

        skin.add("createGameClicked", new Texture("CreateGameBtnClicked.png"));
        skin.add("joinGameClicked", new Texture("JoinGameBtnClicked.png"));
        skin.add("settingsClicked", new Texture("SettingsBtnClicked.png"));
        skin.add("instructionsClicked", new Texture("InstructionsBtnClicked.png"));

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
                game.goToGameScreen();
            }
        });

        joinGameButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //game.goToJoinGameScreen();
                //dispose();
            }
        });

        settingsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //game.goToSettingsScreen();
                //dispose();
            }
        });

        instructionsButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.goToInstructionsScreen();
                dispose();
            }
        });

        //Stage:

        stage.addActor(createGameButton);
        stage.addActor(joinGameButton);
        stage.addActor(settingsButton);
        stage.addActor(instructionsButton);

        Gdx.input.setInputProcessor(stage);
        return stage;

    }

}