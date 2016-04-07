package com.whackamole.game.screens;

import com.badlogic.gdx.Screen;
import com.whackamole.game.model.MainMenuState;
import com.whackamole.game.views.MainMenuStateRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class MainMenuScreen implements Screen {


    private MainMenuState state;
    private MainMenuStateRenderer renderer;

    @Override
    public void show() {
        this.state = new MainMenuState();
        this.renderer = new MainMenuStateRenderer(this.state);
    }

    @Override
    public void render(float delta) {

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
