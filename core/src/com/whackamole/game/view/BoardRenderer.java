package com.whackamole.game.view;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    ShapeRenderer debugrenderer = new ShapeRenderer();

    public BoardRenderer(Board board){
        this.board = board;
        this.camera = new OrthographicCamera(10, 7);
        this.camera.position.set(5, 3.5f, 0);
        this.camera.update();
    }

    public void render(){
        debugrenderer.setProjectionMatrix(camera.combined);
        debugrenderer.begin(ShapeRenderer.ShapeType.Line);
        for(Mole mole: board.getMoles()){

        }
    }
}
