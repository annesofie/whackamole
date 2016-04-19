package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.whackamole.game.model.MoleImage;
import com.whackamole.game.model.Theme;
import net.dermetfan.gdx.assets.AnnotationAssetManager;
import net.dermetfan.gdx.assets.AnnotationAssetManager.Asset;

import java.util.HashMap;

/**
 * Created by Lars on 18.04.2016.
 */
public class Assets {

    public static final AnnotationAssetManager manager = new AnnotationAssetManager();


    // TEXTURES USED FOR REFERENCING TO BASE FILENAMES. THESE ARE NOT LOADED, ONLY USED FOR REFERENCE TO FILENAMES.
    @Asset(value = Texture.class, load = false)
    public static final String
    BOARD_BOTTOM = "b1.png",
    BOARD_SECOND_BOTTOM = "b2.png",
    BOARD_SECOND_TOP = "b3.png",
    BOARD_TOP = "b4.png",
    BOARD_SCORE = "hs.png",

    // Background texture on ready screen
    READYBACKGROUND = "ReadyBackground.png",

    // Moles are defined in the MoleImage enum type
    MOLE0 = MoleImage.getFileNameOnImageId(0),
    MOLE1 = MoleImage.getFileNameOnImageId(1),
    MOLE2 = MoleImage.getFileNameOnImageId(2),
    MOLE3 = MoleImage.getFileNameOnImageId(3),
    MOLE4 = MoleImage.getFileNameOnImageId(4),
    MOLE5 = MoleImage.getFileNameOnImageId(5),

    SPEECH = "speech.m4a",
    BACKGROUND_MUSIC = "background.mp3";


    // THEME SPECIFIC TEXTURES - paths are defined in the Theme enum type
    private static String presPath = Theme.PRESIDENTIAL.path();
    private static String kardPath = Theme.KARDASHIAN.path();

    @Asset(Texture.class)
    public static final String
    PRES_BOARD_BOTTOM = presPath + BOARD_BOTTOM,
    PRES_BOARD_SECOND_BOTTOM = presPath + BOARD_SECOND_BOTTOM,
    PRES_BOARD_SECOND_TOP = presPath + BOARD_SECOND_TOP,
    PRES_BOARD_TOP = presPath + BOARD_TOP,
    PRES_BOARD_SCORE = presPath + BOARD_SCORE,
    PRES_READYBACKGROUND = presPath + READYBACKGROUND,

    KARD_BOARD_BOTTOM = kardPath + BOARD_BOTTOM,
    KARD_BOARD_SECOND_BOTTOM = kardPath + BOARD_SECOND_BOTTOM,
    KARD_BOARD_SECOND_TOP = kardPath + BOARD_SECOND_TOP,
    KARD_BOARD_TOP = kardPath + BOARD_TOP,
    KARD_BOARD_SCORE = kardPath + BOARD_SCORE,
    KARD_READYBACKGROUND = kardPath + READYBACKGROUND,

    PRES_MOLE0 = presPath + MOLE0,
    PRES_MOLE1 = presPath + MOLE1,
    PRES_MOLE2 = presPath + MOLE2,
    PRES_MOLE3 = presPath + MOLE3,
    PRES_MOLE4 = presPath + MOLE4,
    PRES_MOLE5 = presPath + MOLE5,

    KARD_MOLE0 = kardPath + MOLE0,
    KARD_MOLE1 = kardPath + MOLE1,
    KARD_MOLE2 = kardPath + MOLE2,
    KARD_MOLE3 = kardPath + MOLE3,
    KARD_MOLE4 = kardPath + MOLE4,
    KARD_MOLE5 = kardPath + MOLE5;


    // THEME SPECIFIC SOUNDS
    @Asset(Sound.class)
    public static final String
    PRES_SPEECH = presPath + SPEECH,
    KANYESPEECH = kardPath + SPEECH,
    PRES_BACKGROUNDMUSIC = presPath + BACKGROUND_MUSIC,
    KARD_BACKGROUNDMUSIC = kardPath + BACKGROUND_MUSIC;


    // OTHER TEXTURES
    @Asset(Texture.class)
    public static final String
    BACKGROUND = "images/Background.png",
    CREATE_GAME_BTN = "images/CreateGameBtn.png",
    JOIN_GAME_BTN = "images/JoinGameBtn.png",
    SETTINGS_BTN = "images/SettingsBTN.png",
    INSTRUCTIONS_BTN = "images/InstructionsBtn.png",
    CREATE_GAME_BTN_CLICKED = "images/CreateGameBtnClicked.png",
    JOIN_GAME_BTN_CLICKED = "images/JoinGameBtnClicked.png",
    SETTINGS_BTN_CLICKED = "images/SettingsBtnClicked.png",
    INSTRUCTIONS_BTN_CLICKED = "images/InstructionsBtnClicked.png",
    KARDASHIAN_THEME_BTN_SELECTED = "images/KardashianBtnSelected.png",
    PRESEDENTIAL_THEME_BTN_SELECTED = "images/PresedentialBtnSelected.png",

    //
    RETURN_BTN = "images/ReturnBtn.png",
    INSTRUCTIONS = "images/Instructions.png",
    WHITE_RECTANGLE = "images/WhiteRectangle.png",

    // SETTINGS
    PRESEDENTIAL_THEME_BTN = "images/TrumpGame.png",
    KARDASHIAN_THEME_BTN = "images/KardGame.png",
    PLUSBTN = "images/plusbtn.png",
    PLUSBTNCLICKED = "images/plusbtnclicked.png",
    MINUSBTN = "images/minusbtn.png",
    MINUSBTNCLICKED = "images/minusbtnclicked.png",
    SETTINGSBTN = "images/SettingsBTN.png",
    SOUND_ON_BTN = "images/SoundOn.png",
    SOUND_OFF_BTN = "images/SoundOff.png",

    // TEXTFIELDS AND CREATE/JOIN GAME
    ENTERGAMENAME = "textfield/EnterGameName.png",
    ENTERGAMENAMENOTEXT = "textfield/EnterGameNameNoText.png",
    CURSOR = "textfield/cursor.png",
    TEXTFIELD = "textfield/textfield.png",
    INVALIDGAMENAME = "textfield/InvalidGameName.png",
    INVALIDNICKNAME = "textfield/InvalidNickName.png",
    GAMENAMEALREADYEXISTS = "textfield/gamenameallreadyexists.png",
    NOGAMEWITHNAMEEXISTS = "textfield/NoGameWithNameExists.png",
    GAMEISFULL = "textfield/GameIsFull.png",
    ENTERBTN = "images/EnterBtn.png",
    ENTERBTNCLICKED = "images/EnterBtnClicked.png",

    // READY SCREEN
    READYBTN = "images/ReadyBtn.png",
    READYBTNCLICKED = "images/ReadyBtnClicked.png",

    PLAYERLISTBACKGROUND = "images/PlayerListBackground.png";

    // OTHER SOUNDS
    @Asset(Sound.class)
    public static final String
    HITSOUND = "sounds/hit.mp3";



    // FONTS
    /*
    // IF OTHER FONTS / FONT SIZES ARE NEEDED SIMPLY COPY PASTE THE CODE BELOW AND CHANGE THE PARAMETERS
    private static FreeTypeFontGenerator.FreeTypeFontParameter fontParams = new FreeTypeFontGenerator.FreeTypeFontParameter();
    static {
        fontParams.size = Gdx.graphics.getHeight()/7;
        fontParams.color = Color.BLACK;
    }
    */
    @Asset(value = FreeTypeFontGenerator.class, load = false)
    public static final String
    FONT = "fonts/OpenSans-CondBold.ttf";


    public static void dispose() {
        manager.dispose();
    }



}
