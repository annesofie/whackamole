package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.GameOver;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.views.GameOverRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameOverScreen implements Screen, InputProcessor {

    private final ScreenController screenController;
    private GameOver gameOver;
    private GameOverRenderer gameOverRenderer;
    private Preferences prefs;

    GameOverRenderer renderer;


    public GameOverScreen(final ScreenController screenController) {
        this.screenController = screenController;

        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

        this.gameOver = new GameOver();

        this.gameOverRenderer = new GameOverRenderer(gameOver);

    }


    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        renderer.render();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
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
    public void dispose() {}



    // THE REST OF THE INPUTPROCESSOR METHODS

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
