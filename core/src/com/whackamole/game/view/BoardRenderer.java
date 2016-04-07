package com.whackamole.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.whackamole.game.model.Board;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.whackamole.game.model.Mole;


/**
 * Created by oysteinhauan on 07/04/16.
 */
public class BoardRenderer {

    private Board board;
    private OrthographicCamera camera;
    private Texture img;
    private SpriteBatch batch;
    private Sprite sprite;
    private int height, width;

    ShapeRenderer debugrenderer = new ShapeRenderer();

    public BoardRenderer(Board board){
        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.batch = new SpriteBatch();
        this.board = board;
        this.camera = new OrthographicCamera(10, 7);
        this.camera.position.set(5, 3.5f, 0);
        this.camera.update();
    }

    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugrenderer.setProjectionMatrix(camera.combined);
        debugrenderer.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
        batch.draw(new Texture(Gdx.files.internal("b1.png")), 0, 0 , width, 3*height/16);
        batch.draw(new Texture(Gdx.files.internal("b2.png")), 0, 3*height/16, width, 3*height/16);
        batch.draw(new Texture(Gdx.files.internal("b3.png")), 0, 6*height/16, width, 3*height/16);
        batch.draw(new Texture(Gdx.files.internal("b4.png")), 0, 9*height/16, width, height/4);
        batch.end();

        //
        for (Mole mole: board.getMoles()) {
            batch.begin();
            batch.draw(mole.getMoleImage(), mole.getPosition().x - 17*width/120, mole.getPosition().y - height/12, 17*width/60, height/6);
            sprite = new Sprite(mole.getMoleImage());
            sprite.setPosition(mole.getPosition().x, mole.getPosition().y);
            //sprite.draw(batch);
            batch.end();
        }
    }
}
