package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.model.FileName;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.views.GameSettingsRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class SettingsScreen implements Screen {


    private final WhackAMole game;
    private Preferences prefs;
    private GameSettingsRenderer renderer;
    private Skin skin;
    private Stage stage;
    int screenWidth, screenHeight;
    int theme_btn_diameter;
    int soundBtnWidth;
    int returnBtnWidth, returnBtnHeight;
    private CheckBox soundCheckBox;

    //----------------------------------------------------------------

    public SettingsScreen(final WhackAMole game) {

        // Game kan brukes til å endre screen f.eks. game.goToMainScreen();
        this.game = game;

        // Modellen vi jobber med her
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

        this.renderer = new GameSettingsRenderer();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        theme_btn_diameter = (new Texture(FileName.KARDASHIAN_THEME_BTN.filename())).getWidth();

        soundBtnWidth = (new Texture(FileName.SOUND_ON_BTN.filename())).getWidth();
        returnBtnWidth = (new Texture(FileName.RETURN_BTN.filename())).getWidth();
        returnBtnHeight = (new Texture(FileName.RETURN_BTN.filename())).getHeight();

    }

    @Override
    public void show() {
        renderer.loadRenderer(loadActors());
    }

    @Override
    public void render(float delta) {
        this.renderer.render();
    }

    private Stage loadActors(){
        stage = new Stage();
        skin = new Skin();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();

        skin.add("returnBtn", new Texture(FileName.RETURN_BTN.filename()));
        skin.add("presThemeBtn", new Texture(FileName.PRESIDENTIAL_THEME_BTN.filename()));
        skin.add("kardThemeBtn", new Texture(FileName.KARDASHIAN_THEME_BTN.filename()));

        skin.add("soundOnBtn", new Texture(FileName.SOUND_ON_BTN.filename()));
        skin.add("soundOffBtn", new Texture(FileName.SOUND_OFF_BTN.filename()));

        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));
        ImageButton presThemeButton = new ImageButton(skin.getDrawable("presThemeBtn"));
        ImageButton kardThemeButton = new ImageButton(skin.getDrawable("kardThemeBtn"));

        CheckBox.CheckBoxStyle soundCheckBoxStyle  = new CheckBox.CheckBoxStyle(skin.getDrawable("soundOffBtn"),skin.getDrawable("soundOnBtn"),new BitmapFont(),new Color());
        //new ImageButton(skin.getDrawable("soundOffBtn"),skin.getDrawable("soundOnBtn"),skin.getDrawable("soundOnBtn"));

        soundCheckBox = new CheckBox("sound",soundCheckBoxStyle);


        returnButton.setPosition(screenWidth*9/10 - returnBtnWidth*2,screenHeight*8/10 - returnBtnHeight*2);
        presThemeButton.setPosition(screenWidth/2 - screenWidth/20 - theme_btn_diameter, screenHeight/2 - screenHeight/5);
        kardThemeButton.setPosition(screenWidth/2 + screenWidth/20, screenHeight/2 - screenHeight/5);
        soundCheckBox.setPosition(screenWidth/2-soundBtnWidth/2,screenHeight*6/10);

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToMainMenuScreen();
                dispose();
            }
        });

        presThemeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("presthemeClicked");
                prefs.putInteger(Prefs.THEME.key(), 0);
                prefs.flush();
            }
        });

        kardThemeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("kardthemeClicked");
                prefs.putInteger(Prefs.THEME.key(), 1);
                prefs.flush();
            }
        });

        soundCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("soundBtnClicked");
                if(soundCheckBox.isChecked()){
                    //soundCheckBox.setChecked(false);
                    prefs.putBoolean(Prefs.ISSOUND.key(),false);
                    prefs.flush();
                } else {
                    //soundCheckBox.setChecked(true);
                    prefs.putBoolean(Prefs.ISSOUND.key(),true);
                    prefs.flush();
                }
            }
        });


        stage.addActor(returnButton);
        stage.addActor(presThemeButton);
        stage.addActor(kardThemeButton);
        stage.addActor(soundCheckBox);
        Gdx.input.setInputProcessor(stage);
        return stage;
    }

    //----------------------------------------------------------------


    // THE REST OF THE SCREEN METHODS

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }


}
