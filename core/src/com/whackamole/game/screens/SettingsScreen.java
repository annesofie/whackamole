package com.whackamole.game.screens;

import com.badlogic.gdx.Screen;
import com.whackamole.game.views.GameSettingsRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class SettingsScreen implements Screen{



    //private GameSettingsState state;
    private GameSettingsRenderer renderer;

    @Override
    public void show() {
        //this.state = new GameSettingsState();
        //this.renderer = new GameSettingsRenderer(this.state);

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
