package com.whackamole.game.utils;

/**
 * Created by Lars on 12/04/16.
 */
public enum Prefs {

    PREFSKEY ("WhackAMole"),
    ISSOUNDKEY ("isSound"),
    USERNAMEKEY("username"),
    THEMEKEY ("themeID"),
    NUMOFMOLESKEY ("numOfMoles");


    private final String key;

    Prefs(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }




}
