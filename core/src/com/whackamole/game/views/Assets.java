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
    private static final String imgDir = "images/";
    private static final String txtfieldDir = "textfield/";

    @Asset(Texture.class)
    public static final String
    BACKGROUND = imgDir + "Background.png",
    CREATE_GAME_BTN = imgDir + "CreateGameBtn.png",
    JOIN_GAME_BTN = imgDir + "JoinGameBtn.png",
    SETTINGS_BTN = imgDir + "SettingsBTN.png",
    INSTRUCTIONS_BTN = imgDir + "InstructionsBtn.png",
    CREATE_GAME_BTN_CLICKED = imgDir + "CreateGameBtnClicked.png",
    JOIN_GAME_BTN_CLICKED = imgDir + "JoinGameBtnClicked.png",
    SETTINGS_BTN_CLICKED = imgDir + "SettingsBtnClicked.png",
    INSTRUCTIONS_BTN_CLICKED = imgDir + "InstructionsBtnClicked.png",
    KARDASHIAN_THEME_BTN_SELECTED = imgDir + "KardashianBtnSelected.png",
    PRESEDENTIAL_THEME_BTN_SELECTED = imgDir + "PresedentialBtnSelected.png",

    //
    RETURN_BTN = imgDir + "ReturnBtn.png",
    INSTRUCTIONS = imgDir + "Instructions.png",
    WHITE_RECTANGLE = imgDir + "WhiteRectangle.png",

    // SETTINGS
    PRESEDENTIAL_THEME_BTN = imgDir + "TrumpGame.png",
    KARDASHIAN_THEME_BTN = imgDir + "KardGame.png",
    PLUSBTN = imgDir + "plusbtn.png",
    PLUSBTNCLICKED = imgDir + "plusbtnclicked.png",
    MINUSBTN = imgDir + "minusbtn.png",
    MINUSBTNCLICKED = imgDir + "minusbtnclicked.png",
    SETTINGSBTN = imgDir + "SettingsBTN.png",
    SOUND_ON_BTN = imgDir + "SoundOn.png",
    SOUND_OFF_BTN = imgDir + "SoundOff.png",

    // TEXTFIELDS AND CREATE/JOIN GAME
    ENTERGAMENAME = txtfieldDir + "EnterGameName.png",
    ENTERGAMENAMENOTEXT = txtfieldDir + "EnterGameNameNoText.png",
    CURSOR = txtfieldDir + "cursor.png",
    TEXTFIELD = txtfieldDir + "textfield.png",
    INVALIDGAMENAME = txtfieldDir + "InvalidGameName.png",
    INVALIDNICKNAME = txtfieldDir + "InvalidNickName.png",
    GAMENAMEALREADYEXISTS = txtfieldDir + "gamenameallreadyexists.png",
    NOGAMEWITHNAMEEXISTS = txtfieldDir + "NoGameWithNameExists.png",
    GAMEISFULL = txtfieldDir + "GameIsFull.png",
    ENTERBTN = imgDir + "EnterBtn.png",
    ENTERBTNCLICKED = imgDir + "EnterBtnClicked.png",

    // READY SCREEN
    READYBTN = imgDir + "ReadyBtn.png",
    READYBTNCLICKED = imgDir + "ReadyBtnClicked.png",

    PLAYERLISTBACKGROUND = imgDir + "PlayerListBackground.png";


    private static final String soundDir = "sounds/";

    // OTHER SOUNDS
    @Asset(Sound.class)
    public static final String
    HITSOUND = soundDir + "hit.mp3";



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
