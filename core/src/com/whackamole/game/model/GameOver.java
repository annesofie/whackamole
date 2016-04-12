package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.Prefs;

/**
 * Created by Lars on 07/04/16.
 */
public class GameOver {



    Preferences prefs;

    public GameOver() {

        this.prefs = Gdx.app.getPreferences(Prefs.PREFSKEY.key());

    }



}
