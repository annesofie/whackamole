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
import net.dermetfan.gdx.assets.AnnotationAssetManager;

/**
 * Created by Lars on 07/04/16.
 */
public class GameSettingsRenderer implements Renderer, Disposable {


    private Texture background;
    private Texture whiteRectangle;
    private Stage stage;
    private int screenWidth, screenHeight;
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
        stage.getBatch().draw(whiteRectangle, screenWidth*1/10, screenHeight*2/10, screenWidth*8/10, screenHeight*6/10);
        font.draw(stage.getBatch(), Integer.toString(numOfPlayers), screenWidth/2, screenHeight*8/12);
        stage.getBatch().end();
        stage.draw();
    }

    public void loadTextures() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.FONT));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 96;
        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);

        background = Assets.manager.get(Assets.BACKGROUND, Texture.class);
        whiteRectangle = Assets.manager.get(Assets.WHITE_RECTANGLE, Texture.class);


    }


    @Override
    public void dispose() {
        font.dispose();
    }
}
