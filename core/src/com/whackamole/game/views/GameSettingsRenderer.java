package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.whackamole.game.model.GameSettings;
import com.whackamole.game.utils.Prefs;

/**
 * Created by Lars on 07/04/16.
 */
public class GameSettingsRenderer {


    Preferences prefs;



    public GameSettingsRenderer() {
        this.prefs = Gdx.app.getPreferences(Prefs.PREFSKEY.key());

    }


    public void loadRenderer() {
        loadTextures();
    }


    public void render() {

        // Her skal Textures og tekst som ble gjort klart i loadTextures() rendres.

        // Current settings should be displayed to the user
        // The player should be able to select new settings
    }


    private void loadTextures() {

        // Gjør klar bilder og tekst som skal rendres


    }



}
