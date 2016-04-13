package com.whackamole.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.whackamole.game.utils.Prefs;

/**
 * Created by Lars on 12/04/16.
 */
public class GameSettingsController {


    // Modellen vi jobber med her
    Preferences prefs;

    public GameSettingsController() {
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
    }


}
