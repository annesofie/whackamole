package com.whackamole.game.utils;

/**
 * Created by Lars on 12/04/16.
 */
public enum Prefs {

    PREFS("WhackAMole"),
    ISSOUND("isSound"),
    USERNAME("username"),
    THEME("themeID"),
    NUMOFMOLES("numOfMoles");


    private final String key;

    Prefs(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }




}
