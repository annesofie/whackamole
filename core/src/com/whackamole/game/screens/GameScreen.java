package com.whackamole.game.screens;

import com.badlogic.gdx.Screen;
import com.whackamole.game.model.Board;
import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Theme;
import com.whackamole.game.views.BoardRenderer;
import com.whackamole.game.controller.BoardController;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameScreen implements Screen{

    /**
     *  Contains a GameState
     *
     *
     */


    private Board board;
    private BoardRenderer boardRenderer;
    private BoardController controller;
    private Mole mole;

    private Theme th;

    @Override
    public void show() {
        th = Theme.KARDASHIAN;
        board = new Board(th);
        boardRenderer = new BoardRenderer(board, board.getMoles().get(4));
        controller = new BoardController(board);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        boardRenderer.render();
        System.out.println("Testing");

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
