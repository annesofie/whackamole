package com.whackamole.game.utils;

import com.whackamole.game.model.Theme;

/**
 * Created by rubenschmidt on 06.04.2016.
 */
public class Constants {
    //public static final String SERVER_URL = "https://murmuring-everglades-2479.herokuapp.com/";
    //public static final String SERVER_URL = "https://agile-sands-28562.herokuapp.com/";
    //public static final String SERVER_URL = "http://10.0.2.2:5000";
    public static final String SERVER_URL = "http://10.0.0.22:5000";

    public static final float hitBoxRelativeHeight = (float) 33/160;
    public static final float hitBoxRelativeWidth = (float) 260/900;
    public static final float buttonHeightWidthRatio = (float) 154/692;

    public static final int numOfMoles = 1;
    public static final int numOfPlayers = 2;
    public static final Theme theme = Theme.PRESIDENTIAL;
    public static final int themeID = 1;
    public static final boolean isSound = true;
    public static final String username = "";


    // Used to load the grid in board
    public static final int gridDimensions = 3;


}
