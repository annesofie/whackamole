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

    private WhackAMole game;
    private MainMenu state;



    public MainMenuController(WhackAMole game, MainMenu state){
        this.game = game;
        this.state = state;
    }

    public boolean click(int screenX, int screenY){
        if (screenX <= state.getButtonWidth()/2 + state.getButtonWidth()/2 && screenX >= state.getButtonWidth()/2 - state.getButtonWidth()/2){
            int screenHeight = state.getScreenHeight();
            int buttonHeight = state.getButtonHeight();
            if(screenY >= screenHeight*9/12-buttonHeight/2 && screenY <= screenHeight*9/12+buttonHeight/2){
                joinGameClicked();
            }
            else if(screenY >= screenHeight*7/12-buttonHeight/2 && screenY <= screenHeight*7/12+buttonHeight/2){
                createGameClicked();
            }
            else if(screenY >= screenHeight*5/12-buttonHeight/2 && screenY <= screenHeight*5/12+buttonHeight/2){
                settingsClicked();
            }
            else if(screenY >= screenHeight*3/12-buttonHeight/2 && screenY <= screenHeight*3/12+buttonHeight/2){
                instructionsClicked();
            }
            else{
                return false;
            }
        }
        return true;
    }

    private void joinGameClicked(){
        state.setJoinGameBtn(new Texture("JoinGameKlikket.png"));
    }

    private void createGameClicked(){
        state.setCreateGameBtn(new Texture("CreateGameKlikket.png"));
    }

    private void settingsClicked(){
        state.setSettingsBtn(new Texture("SettingsKlikket.png"));
    }

    private void instructionsClicked(){
        state.setInstructionsBtn(new Texture("InstructionsKlikket.png"));
        game.goToInstructions();
    }

    public void goToMainMenu(){
        state.setInstructionsBtn(new Texture("Instructions.png"));
        game.goToMainMenu();
    }


}
