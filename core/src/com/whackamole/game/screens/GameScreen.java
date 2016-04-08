package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.whackamole.game.model.Mole;
import com.badlogic.gdx.graphics.Texture;
import com.whackamole.game.model.Board;
import com.whackamole.game.model.Theme;
import com.whackamole.game.controller.BoardController;
import com.whackamole.game.views.BoardRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameScreen implements Screen{

    private Board board;
    private BoardRenderer boardRenderer;
    private BoardController boardcontroller;
    private Mole mole;

    private Theme th;
    private int currentMole, currentImg;

    @Override
    public void show() {
        th = Theme.KARDASHIAN;
        board = new Board(th);
        //board.setMole(board.getMoles().get(4));
        boardRenderer = new BoardRenderer(board);
        boardcontroller = new BoardController(board);
        currentMole = boardcontroller.getMolePosition();
        currentImg = boardcontroller.getImgPosition();
        boardRenderer = new BoardRenderer(board);
        boardRenderer.setMole(board.getMole(currentMole));
        board.getMoles().get(currentMole).setMoleImg(new Texture(Gdx.files.internal("trump.png")));
        System.out.println(currentMole);

    }

    @Override
    public void render(float delta) {
        boardcontroller.update(delta);
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

    public void setTheme(Theme th){
        this.th = th;
    }
}
