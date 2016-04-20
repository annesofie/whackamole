package com.whackamole.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.whackamole.game.model.Theme;
import com.whackamole.game.views.Assets;

import net.dermetfan.gdx.assets.AnnotationAssetManager;

/**
 * Created by Lars on 19.04.2016.
 */
public class FontGenerator {


    public static BitmapFont kardFont;
    public static BitmapFont presFont;


    public static void generateBitmapFont(Theme theme, float fontToScreenRatio, String fontFile) {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        Assets.manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        Assets.manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params.fontFileName = fontFile;
        params.fontParameters.size = (int)Math.ceil(fontToScreenRatio);
        params.fontParameters.minFilter = Texture.TextureFilter.Linear;
        params.fontParameters.magFilter = Texture.TextureFilter.Linear;

        params.fontParameters.borderColor = getBorderColor(theme);
        params.fontParameters.borderWidth = 3;
        params.fontParameters.color = getFontColor(theme);

        Assets.manager.load(fontFile, BitmapFont.class, params);

        /*
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = Math.round(fontToScreenRatio);
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 5;
        parameter.color = Color.BLUE;
        BitmapFont font = generator.generateFont(parameter);
        //generator.dispose();
        */

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


    // TODO: MIDLERTIDIG LØSNING
    public static void generateKardFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.KARD_FONT_GAME));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        parameter.color = Color.NAVY;
        parameter.borderColor = Color.DARK_GRAY;
        parameter.borderWidth = 4;
        kardFont = generator.generateFont(parameter);
        generator.dispose();
    }

    public static void generatePresFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.PRES_FONT_GAME));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        parameter.color = Color.RED;
        parameter.borderColor = Color.WHITE;
        parameter.borderWidth = 4;
        presFont = generator.generateFont(parameter);
        generator.dispose();
    }

}
