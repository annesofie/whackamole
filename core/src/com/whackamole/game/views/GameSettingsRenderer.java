package com.whackamole.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.whackamole.game.model.GameSettings;

/**
 * Created by Lars on 07/04/16.
 */
public class GameSettingsRenderer {


    GameSettings gameSettings;



    public GameSettingsRenderer(GameSettings gameSettings) {
        this.gameSettings = gameSettings;

    }


    public void loadRenderer() {
        loadTextures();
    }


    public void render() {

        // Her skal Textures og tekst som ble gjort klart i loadTextures() rendres.

        // Current settings should be displayed to the user
        // The current settings can be found in gameSettings above
        // The player should be able to select new settings
    }


    private void loadTextures() {

        // Gjør klar bilder og tekst som skal rendres


    }



}
