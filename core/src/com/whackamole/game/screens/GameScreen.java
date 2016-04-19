package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.*;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.views.Assets;
import com.whackamole.game.views.BoardRenderer;
import com.whackamole.game.controller.BoardController;

public class GameScreen implements Screen, InputProcessor{

    private final ScreenController screenController;
    private Board board;
    private BoardRenderer boardRenderer;
    private BoardController controller;
    private Preferences prefs;
    private Music backgroundmusic;
    private Stage stage;


    public GameScreen(final ScreenController screenController, Stage stage) {

        this.stage = stage;
        stage.clear();
        this.screenController = screenController;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.board = new Board();
        this.boardRenderer = new BoardRenderer(board);
        this.controller = new BoardController(board, screenController);
        loadGame();

    }


    private void loadGame() {

        // Load model
        board.loadBoard();

        // Load renderer
        boardRenderer.loadRenderer(loadActors());

        // Load controller
        controller.loadController();

        // Start the music
        loadSoundtracks();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void render(float delta) {
        controller.update(delta);
        boardRenderer.render();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        int xhit = screenX;
        int yhit = Gdx.graphics.getHeight() - screenY;

        return this.controller.touchDown(xhit, yhit, pointer, button);
    }


    public void loadSoundtracks() {
        boolean isSound = prefs.getBoolean(Prefs.ISSOUND.key());
        if(isSound) {
            Theme theme = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key()));
            backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal(theme.path() + Assets.BACKGROUND_MUSIC));
            backgroundmusic.setLooping(true);
            backgroundmusic.setVolume(0.5f);
            backgroundmusic.play();
        }
    }



    public Stage loadActors() {
        return this.stage;
    }


    @Override
    public void hide() {
        backgroundmusic.stop();
        // dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        boardRenderer.dispose();
    }



    
    // THE REST OF SCREEN METHODS
    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }


    // REST OF THE INPUTPROCESSOR METHODS
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
