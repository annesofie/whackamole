package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.FileName;
import com.whackamole.game.utils.Button;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.Prefs;

/**
 * Created by Lars on 13/04/16.
 */
public class CreateGameRenderer implements Renderer {


    // TEXTURES

    private Texture background;

    private SpriteBatch batch;

    // MODEL
    private CreateGame createGame;

    // GAME PROPERTIES
    Preferences prefs;
    private int canvasHeight, canvasWidth;


    public CreateGameRenderer(CreateGame createGame) {

        this.createGame = createGame;

        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

        this.batch = new SpriteBatch();

        canvasHeight = Gdx.graphics.getHeight();
        canvasWidth = Gdx.graphics.getWidth();

    }


    Stage stage;

    public void loadRenderer(Stage stage) {

        this.stage = stage;
        loadTextures();

    }


    public void render() {

        batch.begin();
        batch.draw(background, 0, 0, canvasWidth, canvasHeight);
        batch.end();

        stage.act();
        stage.draw();

    }


    private void loadTextures() {

        background = new Texture(Gdx.files.internal(FileName.BACKGROUND_GRASS.filename()));

    }




}
