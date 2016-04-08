package com.whackamole.game.screens;

import com.badlogic.gdx.Screen;
import com.whackamole.game.model.MainMenu;
import com.whackamole.game.views.MainMenuRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class MainMenuScreen implements Screen {


    private MainMenu state;
    private MainMenuRenderer renderer;

    @Override
    public void show() {
        this.state = new MainMenu();
        this.renderer = new MainMenuRenderer(this.state);
    }

    @Override
    public void render(float delta) {
        this.renderer.render();
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

}