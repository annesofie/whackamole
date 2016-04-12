package com.whackamole.game.model;

/**
 * Created by Lars on 12/04/16.
 */
public enum BackgroundImage {

    // Used to render the board
    BOARD_BOTTOM("b1.png"),
    BOARD_SECOND_BOTTOM("b2.png"),
    BOARD_SECOND_TOP("b3.png"),
    BOARD_TOP("b3.png"),

    // Simple background images for the mainmenu and other views
    MAINMENU(""),
    NEWGAME(""),
    GAMEOVER(""),
    JOINGAME(""),
    INSTRUCTIONS(""),
    SETTINGS("");


    private final String filename;

    BackgroundImage(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }



}
