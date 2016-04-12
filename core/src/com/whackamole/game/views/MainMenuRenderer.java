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

    private MainMenu mainMenu;
    private OrthographicCamera cam;
    private SpriteBatch batch;

    public MainMenuRenderer(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.cam = new OrthographicCamera();
        this.batch = new SpriteBatch();
    }



    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(mainMenu.getBackground(),0,0,mainMenu.getScreenWidth(),mainMenu.getScreenHeight());
        batch.draw(mainMenu.getCreateGameBtn(),mainMenu.getScreenWidth()/2 - mainMenu.getButtonWidth()/2,mainMenu.getScreenHeight()*9/12 - mainMenu.getButtonHeight()/2);
        batch.draw(mainMenu.getJoinGameBtn(),mainMenu.getScreenWidth()/2 - mainMenu.getButtonWidth()/2,mainMenu.getScreenHeight()*7/12 - mainMenu.getButtonHeight()/2);
        batch.draw(mainMenu.getSettingsBtn(),mainMenu.getScreenWidth()/2 - mainMenu.getButtonWidth()/2,mainMenu.getScreenHeight()*5/12 - mainMenu.getButtonHeight()/2);
        batch.draw(mainMenu.getInstructionsBtn(),mainMenu.getScreenWidth()/2 - mainMenu.getButtonWidth()/2,mainMenu.getScreenHeight()*3/12 - mainMenu.getButtonHeight()/2);
        batch.end();
    }


}