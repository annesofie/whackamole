package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
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
        board.getMoles().get(4).setMoleImg(new Texture(Gdx.files.internal(board.getPath() + "p1.png")));
        board.getMoles().get(5).setMoleImg(new Texture(Gdx.files.internal(board.getPath() + "p2.png")));
        board.getMoles().get(1).setMoleImg(new Texture(Gdx.files.internal(board.getPath() + "p3.png")));
        board.getMoles().get(4);
        board.addCurrentMole(board.getMoles().get(4));
        board.addCurrentMole(board.getMoles().get(5));
        board.addCurrentMole(board.getMoles().get(1));
        //board.setMole(board.getMoles().get(4));
        boardRenderer = new BoardRenderer(board);
        //boardRenderer.setMole(board.getMoles().get(4));
        boardRenderer.loadTextures();
        controller = new BoardController(board);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        boardRenderer.render();
        System.out.println(Gdx.graphics.getDeltaTime());

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

    public void setTheme(Theme th){
        this.th = th;
    }
}
