package com.whackamole.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.whackamole.game.model.Theme;
import com.whackamole.game.views.Assets;

/**
 * Created by Lars on 19.04.2016.
 */
public class FontGenerator {


    public static BitmapFont getBitmapFont(Theme theme, float fontToScreenRatio, String fontFile) {
        System.out.println(fontToScreenRatio);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        //parameter.borderColor = getColor(theme);
        //parameter.color = getColor(theme);
        BitmapFont font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);
        generator.dispose();

        System.out.println("Generated font of size " + Math.round(fontToScreenRatio));

        return font;
    }


    private static Color getColor(Theme theme){
        if(theme.equals(Theme.KARDASHIAN)){
            return new Color(Assets.KARD_FONT_R, Assets.KARD_FONT_G, Assets.KARD_FONT_B, 1);
        }
        else if(theme.equals(Theme.PRESIDENTIAL)){
            return new Color(Assets.PRES_FONT_R, Assets.PRES_FONT_G, Assets.PRES_FONT_B, 1);
        }
        else {
            throw new IllegalArgumentException("That is not a legal theme.");
        }
    }
}
