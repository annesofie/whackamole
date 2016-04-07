package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */


public enum Theme {
    /**
     *
     *  Thought maybe an enum would be nice to use for saving / choosing between themes
     *  We can define constants with references to separate "assets" folders with pictures, sounds, etc.
     *
     *  Maybe also set cathegory constants for each individual picture type based on how many points they award.
     *  Then use those enum constants in the code to dynamically select the correct pictures for each theme.
     *
     */

    PRESEDENTIAL_THEME ("/assets/presedential"),
    KARDASHIAN_THEME ("/assets/kardashian");

    private final String path;

    Theme(String path) {
        this.path = path;
    }

}
