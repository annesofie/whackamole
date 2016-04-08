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

/**
 * Created by oysteinhauan on 07/04/16.
 */
public class MoleRenderer {

    private OrthographicCamera camera;
    private Texture img;
    private SpriteBatch batch;
    private Sprite sprite;
    private int height, width;

    private ShapeRenderer debugrenderer = new ShapeRenderer();
    private Mole mole;

    public MoleRenderer(Mole mole) {

        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.batch = new SpriteBatch();
        this.mole = mole;


    }


    public void render(){
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //debugrenderer.setProjectionMatrix(camera.combined);
        //debugrenderer.begin(ShapeRenderer.ShapeType.Line);


        batch.begin();
        batch.draw(mole.getMoleImage(), mole.getPosition().x - 17*width/120, mole.getPosition().y - height/32, 17*width/60, height/6);
        sprite = new Sprite(mole.getMoleImage());
        sprite.setPosition(mole.getPosition().x, mole.getPosition().y);
        //sprite.draw(batch);
        batch.end();
    }
}

