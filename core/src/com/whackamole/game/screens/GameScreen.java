package com.whackamole.game.screens;

import com.badlogic.gdx.Screen;
import com.whackamole.game.model.GameState;
import com.whackamole.game.views.BoardRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameScreen implements Screen{

    /**
     *  Contains a GameState
     *
     *
     */


    private GameState state;
    private BoardRenderer renderer;


    public GameScreen() {
        this.state = new GameState();
        this.renderer = new BoardRenderer(this.state);
    }


    @Override
    public void show() {


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
