package com.whackamole.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.whackamole.game.model.GameOverState;

/**
 * Created by Lars on 07/04/16.
 */
public class GameOverStateRenderer {


    private GameOverState state;
    private OrthographicCamera cam;


    /**
     *  Renders the game over window
     *
     *
     *
     */

    public GameOverStateRenderer(GameOverState state) {
        this.state = state;
        this.cam = new OrthographicCamera();

    }



    public void render() {

    }




}
