package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.whackamole.game.model.Match;
import com.whackamole.game.model.Player;
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
    private float screenHeight;
    private float screenWidth;
    private BitmapFont font;


    //Textures
    Texture background;

    public ReadyRenderer() {
        this.match = Match.getCurrentMatch();
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.screenHeight = Gdx.graphics.getHeight();
        this.screenWidth = Gdx.graphics.getWidth();
    }


    public void loadRenderer(StageExtension stage) {
        System.out.println("Num of players in ReadyRenderer loadRenderer() = " + match.getNumOfPlayers());
        this.stage = stage;
        loadTextures();

    }

    @Override
    public void render() {

        int numOfReadyPlayers = match.numOfReadyPlayers();
        int numOfPlayers = match.getNumOfPlayers();
        String playerList = getTextualPlayerList();

        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, screenWidth, screenHeight);
        font.draw(stage.getBatch(), "Invite friends to: " + match.getGameName() + "\n\n" + match.getCurrentNickNames().size() + " players have joined.\nNeed "
                + numOfPlayers + " more to start.\n\nPlayers:\n" + playerList, screenWidth*3/20, screenHeight*35/44);
        stage.getBatch().end();
        stage.draw();

    }

    private void loadTextures() {
        Theme theme = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key()));
        if(theme == Theme.KARDASHIAN) {
            font = Assets.manager.get(Assets.KARD_FONT_READY);
        }
        else {
            font = Assets.manager.get(Assets.PRES_FONT_READY);
        }
        background = Assets.manager.get(theme.path() + Assets.READYBACKGROUND, Texture.class);
    }

    private String getTextualPlayerList(){
        String playerList = "";
        int pos = 1;
        List<Player> players = match.getPlayerList();
        for(Player player : players){
            if(player.isReady()) {
                playerList += pos + ". " + player.getNickname() + " - ready\n";
            }
            else {
                playerList += pos + ". " + player.getNickname()+ "\n";
            }

            pos++;
        }
        return playerList;
    }

}
