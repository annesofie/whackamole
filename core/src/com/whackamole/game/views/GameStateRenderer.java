package com.whackamole.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.whackamole.game.model.GameState;

/**
 * Created by Lars on 07/04/16.
 */
public class GameStateRenderer {

    /**
     *  Renders the game during game play.
     *  Equivalent to the WorldRenderer class in the LibGdx MVC example
     *
     */

    private GameState state;
    private OrthographicCamera cam;


    public GameStateRenderer(GameState gameState) {
        this.state = gameState;
        this.cam = new OrthographicCamera();

    }




    public void render() {






    }

}
