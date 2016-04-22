package com.whackamole.game.utils;

import com.whackamole.game.model.Theme;

/**
 * Created by rubenschmidt on 06.04.2016.
 */
public class Constants {
    //public static final String SERVER_URL = "https://murmuring-everglades-2479.herokuapp.com/";
    //public static final String SERVER_URL = "https://agile-sands-28562.herokuapp.com/";
    public static final String SERVER_URL = "http://10.0.0.6:5000";

    public static final float menuButtonWidthRatio = (float)678/900;
    public static final float menuButtonHeightRatio = (float)13/160;
    public static final float roundButtonWidthRatio = (float)278/900;
    public static final float roundButtonHeightRatio = (float)278/900;
    public static final float returnButtonHeightRatio = (float)1/16;
    public static final float returnButtonWidthRatio = (float)101/900;
    public static final float soundButtonWidthRatio = (float)128/900;
    public static final float soundButtonHeightRatio = (float)128/900;
    public static final float textfieldHeightRatio = (float)83/1600;
    public static final float textfieldWidthRatio = (float)470/900;

    public static final int numOfMoles = 1;
    public static final int numOfPlayers = 2;
    public static final Theme theme = Theme.PRESIDENTIAL;
    public static final int themeID = 1;
    public static final boolean isSound = true;
    public static final String username = "";


    // Used to load the grid in board
    public static final int gridDimensions = 3;


}
