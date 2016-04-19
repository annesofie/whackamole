package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.whackamole.game.model.FileName;
import com.whackamole.game.utils.Prefs;

/**
 * Created by Lars on 07/04/16.
 */
public class GameSettingsRenderer implements Renderer {


    private Texture background;
    private Texture whiteRectangle;
    private TextArea textArea;
    private Texture chooseThemeText;
    private Texture headline;
    private Texture musicText;
    private Stage stage;
    private int screenWidth, screenHeight;
    private int headlineHeight;
    private BitmapFont font;
    Preferences prefs;



    public GameSettingsRenderer() {
        //this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
    }


    public void loadRenderer(Stage stage) {
        this.stage = stage;
        loadTextures();
    }


    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int numOfPlayers = prefs.getInteger(Prefs.NUMOFPLAYERS.key());

        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,screenWidth,screenHeight);

        stage.getBatch().draw(chooseThemeText, screenWidth/4, screenHeight*9/20);
        stage.getBatch().draw(headline, screenWidth/4, screenHeight*8/10 - headlineHeight*5/2);
        stage.getBatch().draw(musicText,screenWidth/4, screenHeight*5/10 + 15);

        stage.getBatch().draw(whiteRectangle, screenWidth*1/10, screenHeight*2/10, screenWidth*8/10, screenHeight*6/10);
        font.draw(stage.getBatch(), Integer.toString(numOfPlayers), screenWidth/2, screenHeight*8/12);
        stage.getBatch().end();
        stage.draw();
    }

    private void loadTextures() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FileName.FONT.filename()));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 96;
        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);
        generator.dispose();

        // Lag textures her basert på tema, instruksjoner osv.
        // Antakeligvis bare et bakgrunnsbilde med text på.
        // I render skal disse tegnes.
        background = new Texture(FileName.BACKGROUND.filename());
        whiteRectangle = new Texture(FileName.WHITE_RECTANGLE.filename());
        chooseThemeText = new Texture(FileName.CHOOSE_THEME_TEXT.filename());
        headline = new Texture(FileName.SETTINGS_HEADLINE_TEXT.filename());
        musicText = new Texture(FileName.SETTINGS_MUSIC_TEXT.filename());
        headlineHeight = headline.getHeight();

    }

    //overskrift må heves, music må senkes


}
