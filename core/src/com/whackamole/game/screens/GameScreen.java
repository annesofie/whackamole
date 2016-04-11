package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.whackamole.game.model.Board;
import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Theme;
import com.whackamole.game.views.BoardRenderer;
import com.whackamole.game.controller.BoardController;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameScreen implements Screen, InputProcessor{

    /**
     *  Contains a GameState
     */

    private Board board;
    private BoardRenderer boardRenderer;
    private BoardController controller;
    private Mole mole;
    private Theme th;
    private int currentMole, currentImg;

    @Override
    public void show() {
        th = Theme.KARDASHIAN;
        board = new Board(th);
        board.loadImages();
        boardRenderer = new BoardRenderer(board);
        boardRenderer.loadTextures();
        controller = new BoardController(board);
        Gdx.input.setInputProcessor(this);
    }
    @Override
    public void render(float delta) {
        controller.update(delta);
        boardRenderer.render();
        //System.out.println(Gdx.graphics.getDeltaTime());

    }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }
    @Override
    public void dispose() { }

    public void setTheme(Theme th){
        this.th = th;
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
        System.out.println("Touch");
        return this.controller.touchDown(screenX, board.getHeight() - screenY, pointer, button);
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
