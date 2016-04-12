package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.model.*;
import com.whackamole.game.views.BoardRenderer;
import com.whackamole.game.controller.BoardController;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameScreen implements Screen, InputProcessor{


    // Screens inneholder først og fremst disse: Game, Model, View og Kontroller
    final WhackAMole game;
    private Board board;
    private BoardRenderer boardRenderer;
    private BoardController controller;
    private GameSettings gameSettings;

    // Helt greit at denne starter musikken
    private Music backgroundmusic;


    /**

        GameScreen kan sees på som en del av kontrollerne.
        Den håndterer input fra bruker, initialiserer modellen og viewene. I dette tilfelle Board og BoardRenderer.
        Den delegerer også oppgaver videre til andre kontrollere. I dette tilfelle BoardController først og fremst.
        Ikke så alt for mye bør skje her. Den bør delegere mesteparten av arbeidet videre til andre klasser.

     **/

    public GameScreen(final WhackAMole game) {

        // game kan nå brukes til å endre screens, f.eks. game.goToMainMenuScreen();
        this.game = game;

        // GameSettings er kjekt å ha mange steder. Theme finner man blant annet her
        this.gameSettings = game.getGameSettings();

        // Initialiserer brettet basert på theme, num of moles osv. som alltid er definert i GameSettings
        this.board = new Board(gameSettings);

        // Gir boardRenderer modellen å jobbe med
        this.boardRenderer = new BoardRenderer(board, gameSettings);

        // Gir kontrolleren modellen å jobbe med. Legg merke til at kun kontroller
        controller = new BoardController(board);
    }


    @Override
    public void show() {

        // Load board
        board.loadBoard();

        // Load renderer
        boardRenderer.loadRenderer();

        // Starter musikken
        loadSoundtracks();

        // Setter denne til å lytte på input fra brukeren
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
        if(gameSettings.isSound()) {
            Theme theme = gameSettings.getTheme();
            backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal(theme.path() + FileName.BACKGROUNDMUSIC.filename()));
            backgroundmusic.setLooping(true);
            backgroundmusic.setVolume(0.5f);
            backgroundmusic.play();
        }
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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

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
