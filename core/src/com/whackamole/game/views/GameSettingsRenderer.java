package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Disposable;
import com.whackamole.game.utils.FontGenerator;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.StageExtension;

import net.dermetfan.gdx.assets.AnnotationAssetManager;

/**
 * Created by Lars on 07/04/16.
 */
public class GameSettingsRenderer implements Renderer {


    private Texture background;
    private StageExtension stage;
    private int screenWidth, screenHeight;
    private BitmapFont font;
    Preferences prefs;



    public GameSettingsRenderer() {
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
    }


    public void loadRenderer(StageExtension stage) {
        this.stage = stage;
        loadTextures();
    }


    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int numOfPlayers = prefs.getInteger(Prefs.NUMOFPLAYERS.key());

        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,screenWidth,screenHeight);
        font.draw(stage.getBatch(), Integer.toString(numOfPlayers), screenWidth/2, screenHeight*67/96);
        stage.getBatch().end();
        stage.draw();
    }

    private void loadTextures() {
        font = Assets.manager.get(Assets.SETTINGS_FONT);
        background = Assets.manager.get(Assets.SETTINGS_BACKGROUND, Texture.class);
    }
}
