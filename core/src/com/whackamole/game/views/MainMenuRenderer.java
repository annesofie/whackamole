package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whackamole.game.model.MainMenu;


public class MainMenuRenderer {


    /**
     *  Renders the main menu
     *
     */

    private MainMenu state;
    private OrthographicCamera cam;
    private SpriteBatch batch;

    public MainMenuRenderer(MainMenu state) {
        this.state = state;
        this.cam = new OrthographicCamera();
        this.batch = new SpriteBatch();
    }



    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(state.getBackground(),0,0,state.getScreenWidth(),state.getScreenHeight());
        batch.draw(state.getCreateGameBtn(),state.getScreenWidth()/2 - state.getButtonWidth()/2,state.getScreenHeight()*4/5 - state.getButtonHeight()/2);
        batch.draw(state.getJoinGameBtn(),state.getScreenWidth()/2 - state.getButtonWidth()/2,state.getScreenHeight()*3/5 - state.getButtonHeight()/2);
        batch.draw(state.getSettingsBtn(),state.getScreenWidth()/2 - state.getButtonWidth()/2,state.getScreenHeight()*2/5 - state.getButtonHeight()/2);
        batch.draw(state.getInstructionsBtn(),state.getScreenWidth()/2 - state.getButtonWidth()/2,state.getScreenHeight()*4/5 - state.getButtonHeight()/2);
        batch.end();
    }


}