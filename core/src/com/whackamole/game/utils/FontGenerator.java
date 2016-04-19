package com.whackamole.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.whackamole.game.model.Theme;
import com.whackamole.game.views.Assets;

/**
 * Created by Lars on 19.04.2016.
 */
public class FontGenerator {


    public static BitmapFont kardFont;
    public static BitmapFont presFont;


    public static BitmapFont getBitmapFont(Theme theme, float fontToScreenRatio, String fontFile) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = Math.round(fontToScreenRatio);
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 5;
        parameter.color = Color.BLUE;
        BitmapFont font = generator.generateFont(parameter);
        //generator.dispose();

        System.out.println("Generated font of size " + Math.round(fontToScreenRatio));

        return font;
    }


    private static Color getColor(Theme theme){
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


    // TODO: MIDLERTIDIG LØSNING
    public static void generateKardFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.FONT_KARD));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        parameter.color = Color.NAVY;
        parameter.borderColor = Color.DARK_GRAY;
        parameter.borderWidth = 4;
        kardFont = generator.generateFont(parameter);
        generator.dispose();
    }

    public static void generatePresFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.FONT_PRES));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        parameter.color = Color.RED;
        parameter.borderColor = Color.WHITE;
        parameter.borderWidth = 4;
        presFont = generator.generateFont(parameter);
        generator.dispose();
    }

}
