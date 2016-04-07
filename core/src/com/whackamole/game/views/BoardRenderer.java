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

    private GameState state;
    private OrthographicCamera cam;

    private Board board;
    private OrthographicCamera camera;
    private Texture img;
    private SpriteBatch batch;
    private Sprite sprite;
    private int height, width;
    private String b1, b2, b3, b4, hs;

    ShapeRenderer debugrenderer = new ShapeRenderer();

    public BoardRenderer(Board board, Mole mole){
        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.batch = new SpriteBatch();
        this.board = board;
        this.camera = new OrthographicCamera(10, 7);
        this.camera.position.set(5, 3.5f, 0);
        this.camera.update();
        this.mole = mole;
        if(board.getTheme().equals(Theme.KARDASHIAN)){
            b1 = "1Kar.png"; b2 = "2Kar.png"; b3 = "3Kar.png"; b4 = "4Kar.png"; hs = "HighscoreKar.png";
        }

    }

    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugrenderer.setProjectionMatrix(camera.combined);
        debugrenderer.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
        sprite = new Sprite(mole.getMoleImage());
        //mole.hide();
        sprite.setPosition(mole.getPosition().x, mole.getPosition().y);

        batch.draw(new Texture(Gdx.files.internal(hs)), 0, 13*height/16, width, 3*height/16);
        batch.draw(new Texture(Gdx.files.internal(b4)), 0, 9*height/16, width, height/4);
        if(mole.getLocation() > 5 && mole.getLocation() < 9){
            batch.draw(mole.getMoleImage(), mole.getPosition().x - 17*width/120, mole.getPosition().y - height/32, 17*width/60, height/6);
        }
        batch.draw(new Texture(Gdx.files.internal(b3)), 0, 6*height/16, width, 3*height/16);
        if(mole.getLocation() > 2 && mole.getLocation() < 6){
            batch.draw(mole.getMoleImage(), mole.getPosition().x - 17*width/120, mole.getPosition().y - height/32, 17*width/60, height/6);
        }
        batch.draw(new Texture(Gdx.files.internal(b2)), 0, 3*height/16, width, 3*height/16);
        if(mole.getLocation() < 3){
            batch.draw(mole.getMoleImage(), mole.getPosition().x - 17*width/120, mole.getPosition().y - height/32, 17*width/60, height/6);
        }
        batch.draw(new Texture(Gdx.files.internal(b1)), 0, 0 , width, 3*height/16);



        batch.end();

        //sprite.draw(batch);


        //
        /*for (Mole mole: board.getMoles()) {
            batch.begin();
            batch.draw(mole.getMoleImage(), mole.getPosition().x - 17*width/120, mole.getPosition().y - height/32, 17*width/60, height/6);
            sprite = new Sprite(mole.getMoleImage());
            sprite.setPosition(mole.getPosition().x, mole.getPosition().y);
            //sprite.draw(batch);
            batch.end();
        }*/

    }

    private Mole mole;


    public BoardRenderer(GameState gameState) {
        this.state = gameState;
        this.cam = new OrthographicCamera();

    }






}
