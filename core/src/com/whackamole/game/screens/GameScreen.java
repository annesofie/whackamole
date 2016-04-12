package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.SocketController;
import com.whackamole.game.model.Board;
import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.SocketRetreiver;
import com.whackamole.game.views.BoardRenderer;
import com.whackamole.game.controller.BoardController;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameScreen implements Screen, InputProcessor{

    /**
     *  Contains a GameState
     *
     *
     */

    final WhackAMole game;
    private String gameName;
    private OrthographicCamera camera;
    private Texture b1, b2, b3, b4 , hs, p1, p2, p3, p4, p5, bonus;
    private String s1, s2, s3, s4, s5, path;
    private SpriteBatch batch;
    private Sprite sprite;
    private int height, width;
    private Board board;



    public GameScreen(final WhackAMole game) {
        this.game = game;
        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.batch = new SpriteBatch();
        this.path = board.getPath();
        s1 = "b1.png"; s2 = "b2.png"; s3 = "b3.png"; s4 = "b4.png"; s5 = "hs.png";


    }

    private Board board;
    private BoardRenderer boardRenderer;
    private BoardController controller;
    private Mole mole;
    private SocketController sc;
    private Music backgroundmusic;

    private Theme th;
    private int currentMole, currentImg;



    @Override
    public void show() {
        th = Theme.PRESIDENTIAL;
        board = new Board(th);
        board.loadImages();
        boardRenderer = new BoardRenderer(board);
        boardRenderer.loadTextures();
        controller = new BoardController(board);
        Gdx.input.setInputProcessor(this);
        backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal(th.path() + "background.mp3"));
        backgroundmusic.setLooping(true);
        backgroundmusic.setVolume(0.5f);
        backgroundmusic.play();
    }


    @Override
    public void render(float delta) {
        controller.update(delta);
        boardRenderer.render();
        //System.out.println(Gdx.graphics.getDeltaTime());

    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Touch");
        return this.controller.touchDown(screenX, board.getHeight() - screenY, pointer, button);
    }








    public void loadTextures(){
        b1 = new Texture(Gdx.files.internal(path + s1));
        b2 = new Texture(Gdx.files.internal(path + s2));
        b3 = new Texture(Gdx.files.internal(path + s3));
        b4 = new Texture(Gdx.files.internal(path + s4));
        hs = new Texture(Gdx.files.internal(path + s5));
        p1 = new Texture(Gdx.files.internal(path + "p1.png"));
        p2 = new Texture(Gdx.files.internal(path + "p2.png"));
        p3 = new Texture(Gdx.files.internal(path + "p3.png"));
        p4 = new Texture(Gdx.files.internal(path + "p4.png"));
        p5 = new Texture(Gdx.files.internal(path + "p5.png"));
        bonus = new Texture(Gdx.files.internal(path + "p6.png"));

        //må også laste moleImage
    }













    // THE REST OF SCREEN

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





    // THE REST OF INPUTPROCESSOR

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
