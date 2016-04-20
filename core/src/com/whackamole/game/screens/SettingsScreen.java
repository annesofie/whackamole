package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.model.FileName;
import com.whackamole.game.model.Theme;
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
    float screenWidth, screenHeight, createBtnWidth, minusBtnWidth, plusBtnWidth, minusBtnHeight;
    float theme_btn_diameter;
    float soundBtnWidth;
    float returnBtnWidth, returnBtnHeight;
    private CheckBox soundCheckBox;
    private CheckBox kardCheckBox;
    private CheckBox presCheckBox;
    private ImageButton plusBtn;
    private ImageButton minusBtn;
    private Screen screen;


    public SettingsScreen(final WhackAMole game) {

        // Game kan brukes til å endre screen f.eks. game.goToMainScreen();
        this.game = game;
        this.screen = this;

        // Modellen vi jobber med her
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

        this.renderer = new GameSettingsRenderer();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        theme_btn_diameter = screenWidth/3;//(new Texture(FileName.KARDASHIAN_THEME_BTN.filename())).getWidth();

        soundBtnWidth = 0.16f*screenWidth;//(new Texture(FileName.SOUND_ON_BTN.filename())).getWidth();
        returnBtnWidth = 4*screenWidth/90;//(new Texture(FileName.RETURN_BTN.filename())).getWidth();
        returnBtnHeight = screenHeight/40;//(new Texture(FileName.RETURN_BTN.filename())).getHeight();
        createBtnWidth = 680*screenWidth/900;
        minusBtnWidth = 193*screenWidth/900;
        minusBtnHeight = 0.08125f*screenHeight;


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
        skin.add("kardThemeBtnSelected", new Texture(FileName.KARDASHIANTHEMESELECTED.filename()));
        skin.add("presThemeBtnSelected", new Texture(FileName.PRESEDENTIALTHEMESELECTED.filename()));
        skin.add("minusBtn", new Texture(FileName.MINUSBTN.filename()));
        skin.add("minusBtnClicked", new Texture(FileName.MINUSBTN.filename()));
        skin.add("plusBtn", new Texture(FileName.PLUSBTN.filename()));
        skin.add("plusBtnClicked", new Texture(FileName.PLUSBTNCLICKED.filename()));
        skin.add("createBtn", new Texture(FileName.CREATE_GAME_BTN.filename()));
        skin.add("createBtnClicked", new Texture(FileName.CREATE_GAME_BTN_CLICKED.filename()));


        skin.add("soundOnBtn", new Texture(FileName.SOUND_ON_BTN.filename()));
        skin.add("soundOffBtn", new Texture(FileName.SOUND_OFF_BTN.filename()));

        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));
        ImageButton plusBtn = new ImageButton(skin.getDrawable("plusBtn"), skin.getDrawable("plusBtnClicked"));
        ImageButton minusBtn = new ImageButton(skin.getDrawable("minusBtn"), skin.getDrawable("minusBtnClicked"));
        ImageButton createBtn = new ImageButton(skin.getDrawable("createBtn"), skin.getDrawable("createBtnClicked"));
        //ImageButton presThemeButton = new ImageButton(skin.getDrawable("presThemeBtn"));
        //ImageButton kardThemeButton = new ImageButton(skin.getDrawable("kardThemeBtn"));

        CheckBox.CheckBoxStyle soundCheckBoxStyle  = new CheckBox.CheckBoxStyle(skin.getDrawable("soundOffBtn"), skin.getDrawable("soundOnBtn"), new BitmapFont(), new Color());
        CheckBox.CheckBoxStyle kardCheckBoxStyle = new CheckBox.CheckBoxStyle(skin.getDrawable("kardThemeBtn"), skin.getDrawable("kardThemeBtnSelected"), new BitmapFont(), new Color());
        final CheckBox.CheckBoxStyle presCheckBoxStyle = new CheckBox.CheckBoxStyle(skin.getDrawable("presThemeBtn"), skin.getDrawable("presThemeBtnSelected"), new BitmapFont(), new Color());
        //new ImageButton(skin.getDrawable("soundOffBtn"),skin.getDrawable("soundOnBtn"),skin.getDrawable("soundOnBtn"));

        soundCheckBox = new CheckBox("sound", soundCheckBoxStyle);
        kardCheckBox = new CheckBox("kard", kardCheckBoxStyle);
        presCheckBox = new CheckBox("pres", presCheckBoxStyle);

        plusBtn.setPosition(screenWidth/2 + screenWidth/3 - minusBtnWidth, screenHeight*8/12 - minusBtnHeight/2);
        minusBtn.setPosition(screenWidth/2 - screenWidth/3 , screenHeight*8/12 - minusBtnHeight/2);
        returnButton.setPosition(screenWidth*9/10 - returnBtnWidth*3, screenHeight*8/10 - returnBtnHeight*3);
        createBtn.setPosition(screenWidth/2-createBtnWidth/2, screenHeight*3/12);
        //presThemeButton.setPosition(screenWidth/2 - screenWidth/20 - theme_btn_diameter, screenHeight/2 - screenHeight/5);
        //kardThemeButton.setPosition(screenWidth/2 + screenWidth/20, screenHeight/2 - screenHeight/5);
        soundCheckBox.setPosition(screenWidth/2-soundBtnWidth/2, screenHeight/2);
        kardCheckBox.setPosition(screenWidth/2, screenHeight/2 - screenHeight/6);
        presCheckBox.setPosition(screenWidth/2 - theme_btn_diameter, screenHeight/2 - screenHeight/6);
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
                game.goToMainMenuScreen(screen);
            }
        });

        createBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToCreateGameScreen(screen);
            }
        });


        plusBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.putInteger(Prefs.NUMOFPLAYERS.key(), prefs.getInteger(Prefs.NUMOFPLAYERS.key()) + 1);
                prefs.flush();
                System.out.println(prefs.getInteger(Prefs.NUMOFPLAYERS.key()));
            }
        });

        minusBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.putInteger(Prefs.NUMOFPLAYERS.key(), prefs.getInteger(Prefs.NUMOFPLAYERS.key()) - 1);
                prefs.flush();
                System.out.println(prefs.getInteger(Prefs.NUMOFPLAYERS.key()));
            }
        });

        //presThemeButton
        presCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("presthemeClicked");
                if(presCheckBox.isChecked()) {
                    kardCheckBox.setChecked(false);
                    presCheckBox.setChecked(true);

                }
                else {
                    kardCheckBox.setChecked(false);
                    kardCheckBox.setChecked(true);
                    prefs.putInteger(Prefs.THEME.key(), 0);
                    prefs.flush();
                    game.reloadBoardRenderer();
                }
            }
        });

        //kardThemeButton
        kardCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("kardthemeClicked");
                if(kardCheckBox.isChecked()) {
                    presCheckBox.setChecked(false);
                    kardCheckBox.setChecked(true);
                }
                else {
                    presCheckBox.setChecked(false);
                    prefs.putInteger(Prefs.THEME.key(), 1);
                    prefs.flush();
                    game.reloadBoardRenderer();
                }
            }
        });

//        soundCheckBox.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("soundBtnClicked");
//                if(soundCheckBox.isChecked()){
//                    //soundCheckBox.setChecked(false);
//                    prefs.putBoolean(Prefs.ISSOUND.key(), true);
//                    prefs.flush();
//                } else {
//                    //soundCheckBox.setChecked(true);
//                    prefs.putBoolean(Prefs.ISSOUND.key(), false);
//                    prefs.flush();
//                }
//            }
//        });


        stage.addActor(returnButton);
        setSize(stage.getActors().get(0), returnBtnWidth);

        stage.addActor(minusBtn);
        setSize(stage.getActors().get(1), minusBtnWidth);
        stage.addActor(plusBtn);
        setSize(stage.getActors().get(2), minusBtnWidth);

        //stage.addActor(presThemeButton);
        //stage.addActor(kardThemeButton);
        stage.addActor(createBtn);
        setSize(stage.getActors().get(3), createBtnWidth);

        stage.addActor(kardCheckBox);
        setSize(stage.getActors().get(4), theme_btn_diameter, theme_btn_diameter);

        stage.addActor(presCheckBox);
        setSize(stage.getActors().get(5), theme_btn_diameter, theme_btn_diameter);

//        stage.addActor(soundCheckBox);
//        setSize(stage.getActors().get(6), soundBtnWidth);
        Gdx.input.setInputProcessor(stage);
        return stage;
    }

    public void setSize(Actor actor, float width){
        actor.setWidth(width);
    }

    public void setSize(Actor actor, float width, float height){
        actor.setWidth(width);
        actor.setHeight(height);
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
