package com.whackamole.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.whackamole.game.model.GameOver;

/**
 * Created by Lars on 07/04/16.
 */
public class GameOverRenderer {


    private GameOver state;
    private OrthographicCamera cam;


    /**
     *  Renders the game over window
     *
     *
     *
     */

    public GameOverRenderer(GameOver state) {
        this.state = state;
        this.cam = new OrthographicCamera();

    }



    public void render() {

    }




}
