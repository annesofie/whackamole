package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.GameSettingsController;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.views.GameSettingsRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class SettingsScreen implements Screen, InputProcessor {


    private final WhackAMole game;
    private Preferences prefs;
    private GameSettingsRenderer renderer;
    private GameSettingsController gameSettingsController;




    public SettingsScreen(final WhackAMole game) {

        // Game kan brukes til å endre screen f.eks. game.goToMainScreen();
        this.game = game;

        // Modellen vi jobber med her
        this.prefs = Gdx.app.getPreferences(Prefs.PREFSKEY.key());

        this.renderer = new GameSettingsRenderer();

        // Hjelpekontrolleren til SettingsScreen
        this.gameSettingsController = new GameSettingsController();


    }



    @Override
    public void show() {

        renderer.loadRenderer();

    }

    @Override
    public void render(float delta) {

        this.renderer.render();

    }





    // THE REST OF THE SCREEN METHODS

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
