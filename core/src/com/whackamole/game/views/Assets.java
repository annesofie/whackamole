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
    PLUSBTN = imgDir + "PlusBtn.png",
    PLUSBTNCLICKED = imgDir + "PlusBtnClicked.png",
    MINUSBTN = imgDir + "MinusBtn.png",
    MINUSBTNCLICKED = imgDir + "MinusBtnClicked.png",
    SETTINGSBTN = imgDir + "SettingsBTN.png",
    SOUND_ON_BTN = imgDir + "SoundOn.png",
    SOUND_OFF_BTN = imgDir + "SoundOff.png",

    // TEXTFIELDS AND CREATE/JOIN GAME
    ENTERGAMENAME = txtfieldDir + "EnterGameName.png",
    ENTERGAMENAMENOTEXT = txtfieldDir + "EnterGameNameNoText.png",
    CURSOR = txtfieldDir + "Cursor.png",
    TEXTFIELD = txtfieldDir + "Textfield.png",
    INVALIDGAMENAME = txtfieldDir + "InvalidGameName.png",
    INVALIDNICKNAME = txtfieldDir + "InvalidNickName.png",
    GAMENAMEALREADYEXISTS = txtfieldDir + "GameNameAlreadyExists.png",
    NOGAMEWITHNAMEEXISTS = txtfieldDir + "NoGameWithNameExists.png",
    GAMEISFULL = txtfieldDir + "GameIsFull.png",
    ENTERBTN = imgDir + "EnterBtn.png",
    ENTERBTNCLICKED = imgDir + "EnterBtnClicked.png",

    // READY SCREEN
    READYBTN = imgDir + "ReadyBtn.png",
    READYBTNCLICKED = imgDir + "ReadyBtnClicked.png";



    private static final String soundDir = "sounds/";

    // OTHER SOUNDS
    @Asset(Sound.class)
    public static final String
    HITSOUND = soundDir + "hit.mp3";

    // FONTS
    public static final String
    FONT = "fonts/OpenSans-CondBold.ttf",
    PRES_FONT_GAME = "fonts/united.ttf",
    KARD_FONT_GAME = "fonts/OpenSans-CondBold.ttf",
    PRES_FONT_READY = "fonts/united.ttf",
    KARD_FONT_READY = "fonts/OpenSans-CondBold.ttf";

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


    public static void generateBitmapFont(Theme theme, float fontToScreenRatio, String fontFile) {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        Assets.manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        Assets.manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params.fontFileName = fontFile;
        params.fontParameters.size = (int)Math.ceil(fontToScreenRatio);
        params.fontParameters.minFilter = Texture.TextureFilter.Linear;
        params.fontParameters.magFilter = Texture.TextureFilter.Linear;

        if(theme.equals(Theme.KARDASHIAN)) {
            params.fontParameters.color = Color.VIOLET;
            params.fontParameters.borderColor = Color.WHITE;
        }
        else  {
            params.fontParameters.color = Color.FIREBRICK;
            params.fontParameters.borderColor = Color.NAVY;
        }
        params.fontParameters.borderWidth = 3;

        Assets.manager.load(fontFile, BitmapFont.class, params);
        System.out.println("Generated font of size " + Math.round(fontToScreenRatio));
    }


    private static Color getFontColor(Theme theme){
        if(theme.equals(Theme.KARDASHIAN)){
            return new Color(Assets.KARD_FONT_R, Assets.KARD_FONT_G, Assets.KARD_FONT_B, 1f);
        }
        else if(theme.equals(Theme.PRESIDENTIAL)){
            return new Color(Assets.PRES_FONT_R, Assets.PRES_FONT_G, Assets.PRES_FONT_B, 1f);
        }
        else {
            throw new IllegalArgumentException("That is not a legal theme.");
        }
    }

    private static Color getBorderColor(Theme theme) {
        return new Color(Assets.FONT_BORDER_R, Assets.FONT_BORDER_G, Assets.FONT_BORDER_B, 1f);
    }


    // Used to dispose all assets on app kill
    public static void dispose() {
        manager.dispose();
    }



}
