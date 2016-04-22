package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.*;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.SocketRetreiver;
import com.whackamole.game.utils.StageExtension;
import com.whackamole.game.views.Assets;
import com.whackamole.game.views.BoardRenderer;
import com.whackamole.game.controller.BoardController;

public class GameScreen implements Screen, InputProcessor {

    private Board board;
    private BoardRenderer boardRenderer;
    private BoardController controller;
    private Preferences prefs;
    private Music backgroundmusic;
    private StageExtension stage;
    private final ScreenController screenController;


    public GameScreen(final ScreenController screenController) {

        this.stage = StageExtension.getCleanInstance();
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.board = new Board();
        this.boardRenderer = new BoardRenderer(board);
        this.screenController = screenController;
        this.controller = new BoardController(board, screenController);
        loadGame();

    }


    private void loadGame() {

        // Load model
        board.loadBoard();

        // Load renderer
        boardRenderer.loadRenderer(loadActors());

        // Load helper controller
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
        boardRenderer.render();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        screenY = Gdx.graphics.getHeight() - screenY;

        return this.controller.touchDown(screenX, screenY, pointer, button);
    }


    public void loadSoundtracks() {
        boolean isSound = prefs.getBoolean(Prefs.ISSOUND.key());
        Theme theme = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key()));
        backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal(theme.path() + Assets.BACKGROUND_MUSIC));
        if(isSound) {
            backgroundmusic.setLooping(true);
            backgroundmusic.setVolume(0.5f);
            backgroundmusic.play();
        }
    }


    public StageExtension loadActors() {
        return this.stage;
    }


    @Override
    public void hide() {
        dispose();
        backgroundmusic.stop();
        backgroundmusic.dispose();
        // SocketRetreiver.getInstance().getSocket().emit("left game", "");
        System.out.println("Hide() run in GameScreen.");
    }

    // Nothing to dispose in this class at the moment
    @Override
    public void dispose() {
        controller.dispose();
    }

    // THE REST OF SCREEN METHODS
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {
        System.out.println("Pause() run in GameScreen and 'left game' emitted");
        SocketRetreiver.getInstance().getSocket().emit("left game", "");
    }

    @Override
    public void resume() {
        screenController.goToMainMenuScreen();
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
