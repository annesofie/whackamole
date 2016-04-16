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

    BACKGROUND_GRASS("Background.png"),
    READYBACKGROUND("ready.png"),
    PLAYERLISTBACKGROUND("PlayerListBackground.png"),

    ENTERGAMENAME("textfield/EnterGameName.png"),
    ENTERGAMENAMENOTEXT("textfield/EnterGameNameNoText.png"),
    CURSOR("textfield/cursor.png"),
    TEXTFIELD("textfield/textfield.png"),
    INVALIDGAMENAME("textfield/invalidgamename.png"),
    INVALIDNICKNAME("textfield/invalidnickname.png"),
    GAMENAMEALREADYEXISTS("textfield/gamenamealreadyexists.png"),

    CREATEGAMEBTN("CreateGameBtn.png"),
    CREATEGAMEBTNCLICKED("CreateGameBtnClicked.png"),
    READYBTN("ReadyBtn.png"),
    READYBTNCLICKED("ReadyBtnClicked.png"),

    SETTINGSBTN("SettingsBTN.png"),

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
