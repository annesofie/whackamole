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
    private Texture b1, b2, b3, b4 , hs;
    private SpriteBatch batch;
    private Sprite sprite;
    private int height, width;
    private Mole mole;
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
        batch.begin();

        //debugrenderer.setProjectionMatrix(camera.combined);
        //debugrenderer.begin(ShapeRenderer.ShapeType.Line);
        //sprite = new Sprite(mole.getMoleImage());

        //mole.hide();

        //sprite.setPosition(mole.getPosition().x, mole.getPosition().y);

        batch.draw(hs, 0, 13*height/16, width, 3*height/16);
        batch.draw(b4, 0, 9*height/16, width, height/4);

        for (Mole mole: board.getCurrentMoles()) {
            if(mole.getLocation() > 5 && mole.getLocation() < 9){
                batch.draw(mole.getMoleImage(),
                        mole.getPosition().x,
                        mole.getPosition().y, 17*width/60, height/6);
            }
        }
        batch.draw(b3, 0, 6*height/16, width, 3*height/16);

        for (Mole mole: board.getCurrentMoles()) {
            if(mole.getLocation() > 2 && mole.getLocation() < 6){

                mole.update(0.015f, mole.getTimeLimit());
                batch.draw(mole.getMoleImage(),
                        mole.getPosition().x,
                        mole.getPosition().y, 17*width/60, height/6);
                if(mole.getFinished()){
                    board.removeCurrentMole(mole);
                }
            }
        }
        batch.draw(b2, 0, 3*height/16, width, 3*height/16);

        for (Mole mole: board.getCurrentMoles()) {
            if(mole.getLocation() < 3){
                mole.update(0.015f, mole.getTimeLimit());
                batch.draw(mole.getMoleImage(),
                        mole.getPosition().x,
                        mole.getPosition().y, 17*width/60, height/6);
                if(mole.getFinished()) {
                    board.removeCurrentMole(mole);
                }
            }
        }
        batch.draw(b1, 0, 0 , width, 3*height/16);
        batch.end();

    }

    public void setMole(Mole mole){
        this.mole = mole;
    }

    public void loadTextures(){
        b1 = new Texture(Gdx.files.internal(path + s1));
        b2 = new Texture(Gdx.files.internal(path + s2));
        b3 = new Texture(Gdx.files.internal(path + s3));
        b4 = new Texture(Gdx.files.internal(path + s4));
        hs = new Texture(Gdx.files.internal(path + s5));
        //må også laste moleImage
    }
}
