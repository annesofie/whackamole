package com.whackamole.game.screens;

import com.badlogic.gdx.Screen;
import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Board;
import com.whackamole.game.model.Theme;
import com.whackamole.game.controller.BoardController;
import com.whackamole.game.views.BoardRenderer;
import com.whackamole.game.controller.BoardController;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameScreen implements Screen{

    private Mole mole;
    private Board board;
    private BoardRenderer boardRenderer;
    private BoardController controller;
    private Theme th;
    private int pos;

    @Override
    public void show() {
        th = Theme.KARDASHIAN;
        board = new Board(th);
        controller = new BoardController(board);
        pos = controller.getMolePositoin();
        boardRenderer = new BoardRenderer(board);

    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        boardRenderer.render();

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
