package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.views.Assets;
import com.whackamole.game.views.GameSettingsRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class SettingsScreen implements Screen {


    private final ScreenController screenController;
    private Preferences prefs;
    private GameSettingsRenderer renderer;
    private Skin skin;
    private Stage stage;
    int screenWidth, screenHeight;
    private CheckBox soundCheckBox;
    private CheckBox kardCheckBox;
    private CheckBox presCheckBox;


    public SettingsScreen(final ScreenController screenController) {

        // Game kan brukes til å endre screen f.eks. game.goToMainScreen();
        this.screenController = screenController;

        // Modellen vi jobber med her
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

        this.renderer = new GameSettingsRenderer();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        renderer.loadRenderer(loadActors());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        this.renderer.render();
    }


    private Stage loadActors(){
        stage = new Stage();
        skin = new Skin();

        Texture kardThemeBtn = Assets.manager.get(Assets.KARDASHIAN_THEME_BTN, Texture.class);
        Texture presThemeBtn = Assets.manager.get(Assets.PRESEDENTIAL_THEME_BTN, Texture.class);
        Texture kardThemeBtnSelected = Assets.manager.get(Assets.KARDASHIAN_THEME_BTN_SELECTED, Texture.class);
        Texture presThemeBtnSelected = Assets.manager.get(Assets.PRESEDENTIAL_THEME_BTN_SELECTED, Texture.class);
        Texture soundOffBtn = Assets.manager.get(Assets.SOUND_OFF_BTN, Texture.class);
        Texture soundOnBtn = Assets.manager.get(Assets.SOUND_ON_BTN, Texture.class);
        Texture returnBtn = Assets.manager.get(Assets.RETURN_BTN, Texture.class);
        Texture minusBtn = Assets.manager.get(Assets.MINUSBTN, Texture.class);
        Texture minusBtnClicked = Assets.manager.get(Assets.MINUSBTNCLICKED, Texture.class);
        Texture plusBtn = Assets.manager.get(Assets.PLUSBTN, Texture.class);
        Texture plusBtnClicked = Assets.manager.get(Assets.PLUSBTNCLICKED, Texture.class);

        int theme_btn_diameter = kardThemeBtn.getWidth();
        int soundBtnWidth = soundOnBtn.getWidth();
        int returnBtnWidth = returnBtn.getWidth();
        int returnBtnHeight = returnBtn.getHeight();

        skin.add("returnBtn", returnBtn);
        skin.add("presThemeBtn", presThemeBtn);
        skin.add("kardThemeBtn", kardThemeBtn);
        skin.add("kardThemeBtnSelected", kardThemeBtnSelected);
        skin.add("presThemeBtnSelected", presThemeBtnSelected);
        skin.add("minusBtn", minusBtn);
        skin.add("minusBtnClicked", minusBtnClicked);
        skin.add("plusBtn", plusBtn);
        skin.add("plusBtnClicked", plusBtnClicked);
        skin.add("soundOnBtn", soundOnBtn);
        skin.add("soundOffBtn", soundOffBtn);

        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));
        ImageButton plusButton = new ImageButton(skin.getDrawable("plusBtn"), skin.getDrawable("plusBtnClicked"));
        ImageButton minusButton = new ImageButton(skin.getDrawable("minusBtn"), skin.getDrawable("minusBtnClicked"));

        CheckBox.CheckBoxStyle soundCheckBoxStyle  = new CheckBox.CheckBoxStyle(skin.getDrawable("soundOffBtn"), skin.getDrawable("soundOnBtn"), new BitmapFont(), new Color());
        CheckBox.CheckBoxStyle kardCheckBoxStyle = new CheckBox.CheckBoxStyle(skin.getDrawable("kardThemeBtn"), skin.getDrawable("kardThemeBtnSelected"), new BitmapFont(), new Color());
        final CheckBox.CheckBoxStyle presCheckBoxStyle = new CheckBox.CheckBoxStyle(skin.getDrawable("presThemeBtn"), skin.getDrawable("presThemeBtnSelected"), new BitmapFont(), new Color());

        soundCheckBox = new CheckBox("sound", soundCheckBoxStyle);
        kardCheckBox = new CheckBox("kard", kardCheckBoxStyle);
        presCheckBox = new CheckBox("pres", presCheckBoxStyle);

        plusButton.setPosition(screenWidth/2 + screenWidth/20 + 50, screenHeight*8/12 - plusBtn.getHeight() + 50);
        minusButton.setPosition(screenWidth/2 - screenWidth/20 - minusBtn.getWidth(), screenHeight*8/12 - minusBtn.getHeight() + 50);
        returnButton.setPosition(screenWidth*9/10 - returnBtnWidth*3, screenHeight*8/10 - returnBtnHeight*3);
        soundCheckBox.setPosition(screenWidth/2-soundBtnWidth/2, screenHeight*5/10);
        kardCheckBox.setPosition(screenWidth/2 + screenWidth/20, screenHeight/2 - screenHeight/5);
        presCheckBox.setPosition(screenWidth/2 - screenWidth/20 - theme_btn_diameter, screenHeight/2 - screenHeight/5);
        if(prefs.getBoolean(Prefs.ISSOUND.key())) {
            soundCheckBox.setChecked(true);
        }
        else {
            soundCheckBox.setChecked(false);
        }
        if(Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key())) == Theme.KARDASHIAN) {
            kardCheckBox.setChecked(true);
            presCheckBox.setChecked(false);
        }
        else {
            kardCheckBox.setChecked(false);
            presCheckBox.setChecked(true);
        }

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToMainMenuScreen();
            }
        });

        plusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.putInteger(Prefs.NUMOFPLAYERS.key(), prefs.getInteger(Prefs.NUMOFPLAYERS.key()) + 1);
                prefs.flush();
                System.out.println(prefs.getInteger(Prefs.NUMOFPLAYERS.key()));
            }
        });

        minusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.putInteger(Prefs.NUMOFPLAYERS.key(), prefs.getInteger(Prefs.NUMOFPLAYERS.key()) - 1);
                prefs.flush();
                System.out.println(prefs.getInteger(Prefs.NUMOFPLAYERS.key()));
            }
        });

        // Presedential theme button
        presCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(prefs.getInteger(Prefs.THEME.key()) == Theme.PRESIDENTIAL.getId()) {
                    kardCheckBox.setChecked(false);
                    presCheckBox.setChecked(true);

                }
                else {
                    kardCheckBox.setChecked(false);
                    kardCheckBox.setChecked(true);
                    prefs.putInteger(Prefs.THEME.key(), Theme.KARDASHIAN.getId());
                    prefs.flush();
                    System.out.println("Presidential theme now selected.");
                }
            }
        });

        // Kardashian theme button
        kardCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(prefs.getInteger(Prefs.THEME.key()) == Theme.KARDASHIAN.getId()) {
                    presCheckBox.setChecked(false);
                    kardCheckBox.setChecked(true);
                }
                else {
                    presCheckBox.setChecked(false);
                    prefs.putInteger(Prefs.THEME.key(), Theme.PRESIDENTIAL.getId());
                    prefs.flush();
                    System.out.println("Kardashian theme now selected.");
                }
            }
        });

        soundCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("soundBtnClicked");
                if(soundCheckBox.isChecked()){
                    //soundCheckBox.setChecked(false);
                    prefs.putBoolean(Prefs.ISSOUND.key(), true);
                    prefs.flush();
                } else {
                    //soundCheckBox.setChecked(true);
                    prefs.putBoolean(Prefs.ISSOUND.key(), false);
                    prefs.flush();
                }
            }
        });

        stage.addActor(returnButton);
        stage.addActor(minusButton);
        stage.addActor(plusButton);
        stage.addActor(kardCheckBox);
        stage.addActor(presCheckBox);
        stage.addActor(soundCheckBox);
        return stage;
    }


    @Override
    public void hide() {
        dispose();
    }


    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
        renderer.dispose();
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


}
