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
import com.whackamole.game.utils.StageExtension;

import java.util.List;

/**
 * Created by Lars on 15/04/16.
 */
public class ReadyRenderer implements Renderer {


    Match match;
    StageExtension stage;
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


    public void loadRenderer(StageExtension stage) {
        this.stage = stage;
        loadTextures();

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

    private void loadTextures() {
        Theme theme = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key()));
        background = Assets.manager.get(theme.path() + Assets.READYBACKGROUND, Texture.class);

    }
}
