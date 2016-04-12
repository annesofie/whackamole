package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.MainMenuController;
import com.whackamole.game.model.MainMenu;
import com.whackamole.game.views.MainMenuRenderer;


/**
 * Created by AnneSofie on 04.04.2016.
 */
public class MainMenuScreen implements Screen, InputProcessor {


    private MainMenu mainMenu;
    private MainMenuRenderer renderer;
    private MainMenuController controller;
    final WhackAMole game;


    public MainMenuScreen(final WhackAMole game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        mainMenu = new MainMenu();
        renderer = new MainMenuRenderer(mainMenu);
        controller = new MainMenuController(mainMenu, game);
        //this.controller = new MainMenuController(this, this.state);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
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


    }

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
        if (screenX <= mainMenu.getButtonWidth() / 2 + mainMenu.getButtonWidth() / 2 && screenX >= mainMenu.getButtonWidth() / 2 - mainMenu.getButtonWidth() / 2) {
            int screenHeight = mainMenu.getScreenHeight();
            int buttonHeight = mainMenu.getButtonHeight();
            if (screenY >= screenHeight * 9 / 12 - buttonHeight / 2 && screenY <= screenHeight * 9 / 12 + buttonHeight / 2) {
                controller.joinGameClicked();
                dispose();
            } else if (screenY >= screenHeight * 7 / 12 - buttonHeight / 2 && screenY <= screenHeight * 7 / 12 + buttonHeight / 2) {
                controller.createGameClicked();
                dispose();
            } else if (screenY >= screenHeight * 5 / 12 - buttonHeight / 2 && screenY <= screenHeight * 5 / 12 + buttonHeight / 2) {
                controller.settingsClicked();
                dispose();
            } else if (screenY >= screenHeight * 3 / 12 - buttonHeight / 2 && screenY <= screenHeight * 3 / 12 + buttonHeight / 2) {
                controller.instructionsClicked();
                dispose();

            } else {
                return false;
            }
        }
        return true;
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