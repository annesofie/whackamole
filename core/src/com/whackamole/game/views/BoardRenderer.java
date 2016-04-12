package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.whackamole.game.model.Board;
import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Theme;

/**
 * Created by Lars on 07/04/16.
 */
public class BoardRenderer {

    /**
     *  Renders the game during game play.
     *  Equivalent to the WorldRenderer class in the LibGdx MVC example
     *
     */

    private Board board;
    private OrthographicCamera camera;
    private Texture b1, b2, b3, b4 , hs, p1, p2, p3, p4, p5, bonus;
    private SpriteBatch batch;
    private Sprite sprite;
    private int height, width;
    private Mole currentMole;
    private boolean show;

    private String s1, s2, s3, s4, s5, path;

    ShapeRenderer debugrenderer = new ShapeRenderer();

    public BoardRenderer(Board board){
        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.batch = new SpriteBatch();
        this.board = board;
        this.camera = new OrthographicCamera(10, 7);
        this.camera.position.set(5, 3.5f, 0);
        this.camera.update();
        s1 = "b1.png"; s2 = "b2.png"; s3 = "b3.png"; s4 = "b4.png"; s5 = "hs.png";
        this.path = board.getPath();

    }

    public void render(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentMole = board.getCurrentMole();

        batch.begin();
        batch.draw(hs, 0, 13*height/16, width, 3*height/16);
        batch.draw(b4, 0, 9*height/16, width, height/4);
        drawMole(5,9);
        batch.draw(b3, 0, 6*height/16, width, 3*height/16);
        drawMole(2,6);
        batch.draw(b2, 0, 3*height/16, width, 3*height/16);
        drawMole(-1, 3);
        batch.draw(b1, 0, 0 , width, 3*height/16);
        batch.end();



    }

    public void setMole(Mole mole){
        this.currentMole = mole;
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

    private void drawMole(int start, int end){
        if(currentMole!= null && currentMole.getLocation() > start && currentMole.getLocation() < end){
            if(currentMole.getFinished()){
                currentMole.reset();
                //board.removeCurrentMole();
            }
            else {
                currentMole.update(0.015f);
                batch.draw(currentMole.getMoleImage(),
                        currentMole.getPosition().x,
                        currentMole.getPosition().y, 17 * width / 60, height / 6);
            }
        }
    }
}
