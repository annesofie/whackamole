package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
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
    READY_BTN = "ReadyBtn.png",
    READY_BTN_CLICKED = "ReadyBtnClicked.png",

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
    PRES_READY_BTN = presPath + READY_BTN,
    PRES_READY_BTN_CLICKED = presPath + READY_BTN,

    KARD_BOARD_BOTTOM = kardPath + BOARD_BOTTOM,
    KARD_BOARD_SECOND_BOTTOM = kardPath + BOARD_SECOND_BOTTOM,
    KARD_BOARD_SECOND_TOP = kardPath + BOARD_SECOND_TOP,
    KARD_BOARD_TOP = kardPath + BOARD_TOP,
    KARD_BOARD_SCORE = kardPath + BOARD_SCORE,
    KARD_READYBACKGROUND = kardPath + READYBACKGROUND,
    KARD_READY_BTN = kardPath + READY_BTN,
    KARD_READY_BTN_CLICKED = kardPath + READY_BTN_CLICKED,

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
    private static final String settingsDir = "settings/";

    @Asset(Texture.class)
    public static final String
    BACKGROUND = imgDir + "Background.png",
    MAIN_MENU_BACKGROUND = imgDir + "MainMenuBackground.png",
    NEW_GAME_BACKGROUND = imgDir + "NewGameBackground.png",
    JOIN_GAME_BACKROUND = imgDir + "JoinGameBackground.png",
    SETTINGS_BACKGROUND = settingsDir + "SettingsBackground.png",
    GAME_OVER_BACKGROUND = imgDir + "GameOverBackground.png",



    PROCEED_BTN = imgDir + "ProceedBtn.png",
    PROCEED_BTN_CLICKED = imgDir + "ProceedBtnClicked.png",
    JOIN_GAME_BTN = imgDir + "JoinGameBtn.png",
    INSTRUCTIONS_BTN = imgDir + "InstructionsBtn.png",
    CREATE_GAME_BTN = imgDir + "CreateGameBtn.png",
    CREATE_GAME_BTN_CLICKED = imgDir + "CreateGameBtnClicked.png",
    JOIN_GAME_BTN_CLICKED = imgDir + "JoinGameBtnClicked.png",
    INSTRUCTIONS_BTN_CLICKED = imgDir + "InstructionsBtnClicked.png",
    KARDASHIAN_THEME_BTN_SELECTED = imgDir + "KardashianBtnSelected.png",
    PRESEDENTIAL_THEME_BTN_SELECTED = imgDir + "PresedentialBtnSelected.png",


    //
    LARGE_BACK_BTN = imgDir + "LargeBackBtn.png",
    RETURN_BTN = imgDir + "ReturnBtn.png",
    INSTRUCTIONS = imgDir + "Instructions.png",
    WHITE_RECTANGLE = imgDir + "WhiteRectangle.png",
    GAME_OVER_HEADLINE = imgDir + "GameOverHeadline.png",

    // SETTINGS
    PRESEDENTIAL_THEME_BTN = imgDir + "TrumpGame.png",
    KARDASHIAN_THEME_BTN = imgDir + "KardGame.png",
    PLUSBTN = imgDir + "PlusBtn.png",
    PLUSBTNCLICKED = imgDir + "PlusBtnClicked.png",
    MINUSBTN = imgDir + "MinusBtn.png",
    MINUSBTNCLICKED = imgDir + "MinusBtnClicked.png",
    SETTINGS_BTN = settingsDir + "SettingsBtn1.png",
    SETTINGS_BTN_CLICKED = settingsDir + "SettingsBtnClicked.png",
    MUTE_BTN = imgDir + "Mute.png",
    UNMUTE_BTN = imgDir + "Unmute.png",
    SETTINGS_HEADLINE = settingsDir + "SettingsHeadline.png",
    SETTINGS_CHOOSETHEME = settingsDir + "SettingsChooseTheme.png",

    // TEXTFIELDS AND CREATE/JOIN GAME
    ENTERGAMENAME = txtfieldDir + "EnterGameName.png",
    ENTERGAMENAMENOTEXT = txtfieldDir + "EnterGameNameNoText.png",
    CURSOR = txtfieldDir + "Cursor.png",
    TEXTFIELD = txtfieldDir + "Textfield1.png",
    INVALIDGAMENAME = txtfieldDir + "InvalidGameName.png",
    INVALIDNICKNAME = txtfieldDir + "InvalidNickName.png",
    GAMENAMEALREADYEXISTS = txtfieldDir + "GameNameAlreadyExists.png",
    NOGAMEWITHNAMEEXISTS = txtfieldDir + "NoGameWithNameExists.png",
    NICKNAMETAKEN = txtfieldDir + "NickNameTaken.png",
    UNABLETOCONNECT = txtfieldDir + "UnableToConnect.png",
    GAMEISFULL = txtfieldDir + "GameIsFull.png",
    ENTERBTN = imgDir + "EnterBtn.png",
    ENTERBTNCLICKED = imgDir + "EnterBtnClicked.png";



    private static final String soundDir = "sounds/";

    // OTHER SOUNDS
    @Asset(Sound.class)
    public static final String
    HITSOUND = soundDir + "hit.mp3";

    // FONTS
    public static final String
    FONT = "fonts/OpenSans-CondBold.ttf",
    PRES_FONT_GAME = "fonts/FontPresGame.ttf",
    KARD_FONT_GAME = "fonts/FontKardGame.ttf",
    PRES_FONT_READY = "fonts/FontPresReady.ttf",
    KARD_FONT_READY = "fonts/FontKardReady.ttf",
    GAME_OVER_FONT = "fonts/FontGameOver.ttf",
    SETTINGS_FONT = "fonts/FontSettings.ttf",
    TEXTFIELD_FONT = "fonts/FontTextfield.ttf";

    public static final int
    PRES_FONT_R = 15,
    PRES_FONT_G = 52,
    PRES_FONT_B = 104,
    FONT_BORDER_R = 180,
    FONT_BORDER_G = 180,
    FONT_BORDER_B = 180,
    KARD_FONT_R = 61,
    KARD_FONT_G = 66,
    KARD_FONT_B = 98;


    public static void generateThemeBitmapFont(Theme theme, float fontToScreenRatio, String fontFile) {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        Assets.manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        Assets.manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params.fontFileName = fontFile;
        params.fontParameters.size = (int)Math.ceil(fontToScreenRatio);
        params.fontParameters.minFilter = Texture.TextureFilter.Linear;
        params.fontParameters.magFilter = Texture.TextureFilter.Linear;

        if(theme.equals(Theme.KARDASHIAN)) {
            params.fontParameters.color = Color.WHITE;
            params.fontParameters.borderColor = Color.DARK_GRAY;
        }
        else  {
            params.fontParameters.color = Color.FIREBRICK;
            params.fontParameters.borderColor = Color.WHITE;
        }
        params.fontParameters.borderWidth = 2;

        Assets.manager.load(fontFile, BitmapFont.class, params);
        System.out.println("Generated font of size " + Math.round(fontToScreenRatio));
    }

    public static void generatePlainBitmapFont(float fontToScreenRatio, String fontFile) {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        Assets.manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        Assets.manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params.fontFileName = fontFile;
        params.fontParameters.size = (int)Math.ceil(fontToScreenRatio);
        params.fontParameters.minFilter = Texture.TextureFilter.Linear;
        params.fontParameters.magFilter = Texture.TextureFilter.Linear;

        params.fontParameters.color = Color.BLACK;

        Assets.manager.load(fontFile, BitmapFont.class, params);
        System.out.println("Generated font of size " + Math.round(fontToScreenRatio));
    }


    // Used to dispose all assets on app kill
    public static void dispose() {
        manager.dispose();
    }



}
