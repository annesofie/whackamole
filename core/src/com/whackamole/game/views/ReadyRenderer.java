package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.whackamole.game.model.Match;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.Prefs;

import java.util.List;

/**
 * Created by Lars on 15/04/16.
 */
public class ReadyRenderer implements Renderer, Disposable{


    Match match;
    Stage stage;
    Preferences prefs;
    private float canvasHeight;
    private float canvasWidth;
    BitmapFont font;


    //Textures
    Texture background;

    public ReadyRenderer() {
        this.match = Match.getCurrentMatch();
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.canvasHeight = Gdx.graphics.getHeight();
        this.canvasWidth = Gdx.graphics.getWidth();
    }


    public void loadRenderer(Stage stage) {
        this.stage = stage;
        loadTextures();

        /*
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.FONT));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 96;
        font = generator.generateFont(parameter);
        generator.dispose();
        */

    }

    @Override
    public void render() {

        //TODO: MANGLER Å RENDRE HVILKE SPILLERE SOM HAR MELDT SEG PÅ OG HVOR MANGE SOM HAR MELDT 'READY'.
        //TODO: DENNE INFOEN FINNES I VARIABLENE UNDER :)
        List<String> currentNickNames = match.getCurrentNickNames();
        int numOfReadyPlayers = match.numOfReadyPlayers();

        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, canvasWidth, canvasHeight);
        stage.getBatch().end();
        stage.draw();

    }


    public void loadTextures() {
        Theme theme = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key()));
        background = Assets.manager.get(theme.path() + Assets.READYBACKGROUND, Texture.class);

    }


    @Override
    public void dispose() {
        //font.dispose();
    }
}
