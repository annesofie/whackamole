package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.SocketController;
import com.whackamole.game.model.Board;
import com.whackamole.game.model.GameSettings;
import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.SocketRetreiver;
import com.whackamole.game.views.BoardRenderer;
import com.whackamole.game.controller.BoardController;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameScreen implements Screen, InputProcessor{


    // Screens inneholder først og fremst disse: Game, Model, View og Kontroller
    final WhackAMole game;
    private Board board;
    private BoardRenderer boardRenderer;
    private BoardController controller;

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

        // Initialiserer brettet basert på theme, num of moles osv. som alltid er definert i GameSettings
        GameSettings gameSettings = game.getGameSettings();
        this.board = new Board(gameSettings);

        // Gir boardRenderer modellen å jobbe med
        this.boardRenderer = new BoardRenderer(board);

        // Gir kontrolleren modellen å jobbe med. Legg merke til at kun kontroller
        controller = new BoardController(board);
    }


    @Override
    public void show() {
        // Load images on setScreen()
        board.loadImages();

        // Gjør klar renderer til å rendre board modellen
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
        backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal(th.path() + "background.mp3"));
        backgroundmusic.setLooping(true);
        backgroundmusic.setVolume(0.5f);
        backgroundmusic.play();
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
