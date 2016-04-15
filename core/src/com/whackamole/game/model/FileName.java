package com.whackamole.game.model;

/**
 * Created by Lars on 12/04/16.
 */
public enum FileName {

    // Used to render the board
    BOARD_BOTTOM("b1.png"),
    BOARD_SECOND_BOTTOM("b2.png"),
    BOARD_SECOND_TOP("b3.png"),
    BOARD_TOP("b4.png"),
    BOARD_SCORE("hs.png"),

    // Simple background images for the mainmenu and other views
    MAINMENU(""),
    NEWGAME(""),
    GAMEOVER(""),
    JOINGAME(""),
    INSTRUCTIONS(""),
    SETTINGS(""),

    FONT("fonts/OpenSans-CondLight.ttf"),

    BACKGROUND_GRASS("Bakgr.png"),

    ENTERGAMENAME("textfield/EnterGameName.png"),
    ENTERGAMENAMENOTEXT("textfield/EnterGameNameNoText.png"),
    CURSOR("textfield/cursor.png"),
    TEXTFIELD("textfield/textfield.png"),
    INVALIDGAMENAME("textfield/invalidgamename.png"),

    CREATEGAMEBTN("CreateGame.png"),
    CREATEGAMEBTNCLICKED("CreateGameKlikket.png"),

    // Sounds
    BACKGROUNDMUSIC("background.mp3");


    private final String filename;

    FileName(String filename) {
        this.filename = filename;
    }

    public String filename() {
        return this.filename;
    }



}
