package com.whackamole.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.model.MainMenu;
import com.whackamole.game.screens.InstructionScreen;
import com.whackamole.game.screens.MainMenuScreen;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.screenWidth;

/**
 * Created by Ørjan on 11.04.2016.
 */
public class MainMenuController {

    private MainMenu mainMenu;
    final WhackAMole game;



    public MainMenuController(MainMenu mainMenu, WhackAMole game){
        this.mainMenu = mainMenu;
        this.game = game;
    }

    /** The main update method **/
    public void update(float delta) {

    }

    public void joinGameClicked(){
        mainMenu.setJoinGameBtn(new Texture("JoinGameKlikket.png"));
    }

    public void createGameClicked(){
        mainMenu.setCreateGameBtn(new Texture("CreateGameKlikket.png"));
    }

    public void settingsClicked(){
        mainMenu.setSettingsBtn(new Texture("SettingsKlikket.png"));
    }

    public void instructionsClicked(){
        mainMenu.setInstructionsBtn(new Texture("InstructionsKlikket.png"));
        game.setScreen(new InstructionScreen(game));
    }
}
