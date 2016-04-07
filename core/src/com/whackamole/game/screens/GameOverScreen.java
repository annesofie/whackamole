package com.whackamole.game.screens;

import com.badlogic.gdx.Screen;
import com.whackamole.game.model.GameOverState;
import com.whackamole.game.views.GameOverStateRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameOverScreen implements Screen {

    GameOverState state;
    GameOverStateRenderer renderer;


    @Override
    public void show() {
        this.state = new GameOverState();
        this.renderer = new GameOverStateRenderer(this.state);
    }

    @Override
    public void render(float delta) {
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
}
