package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.whackamole.game.model.*;
import com.whackamole.game.utils.FontGenerator;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.StageExtension;

import java.util.List;


public class BoardRenderer implements Renderer {


    private Board board;
    private Preferences prefs;
    // TEXTURES
    private Texture board_bottom, board_second_bottom, board_second_top, board_top, board_score;
    private Array<Texture> moleImages;
    private StageExtension stage;
    // GAME PROPERTIES
    private int height, width;
    private Mole currentMole;
    private BitmapFont font;
    private BitmapFont getReadyFont;
    private Match match;
    private Theme theme;
    private boolean isFirstMole;
    String themePath;
    String themeId;



    public BoardRenderer(Board board){

        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.match = Match.getCurrentMatch();

        this.board = board;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.moleImages = new Array<Texture>();
        this.isFirstMole = true;

    }

    public void loadRenderer(StageExtension stage) {
        // Updating current themepath and themeId to match the selected theme
        this.stage = stage;
        this.theme = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key()));
        this.themePath = theme.path();
        this.themeId = theme.idAsString();

        loadTextures();
    }


    // Render to the screen
    public void render(){
        //Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentMole = board.getCurrentMole();
        boolean hitTheLastMole = board.hitTheLastMole();
        boolean isFirstMole = board.isFirstMole();
        int lastMolePoints = board.getLastMolePoints();
        String leaderBoard = getLeaderBoard();

        stage.getBatch().begin();

        // LEADERBOARD
        stage.getBatch().draw(board_score, 0, 13*height/16, width, 3*height/16);
        font.draw(stage.getBatch(), "Leaderboard:\n" + leaderBoard, width/20, height - (board_score.getHeight()*2/12));

        if(isFirstMole) {
            getReadyFont.draw(stage.getBatch(), "GET READY!\nFirst player to 5000 points wins!+ " + Integer.toString(lastMolePoints) + " points.", width/10, height - (board_score.getHeight()*6/16));
        }
        else if(hitTheLastMole) {
            font.draw(stage.getBatch(), "YOU WERE FAST!\n+ " + Integer.toString(lastMolePoints) + " points.", width*26/50, height - (board_score.getHeight()*6/16));
            //font.draw(stage.getBatch(), "+ " + Integer.toString(lastMolePoints) + " points.", width/2, (height - board_score.getHeight()*9/16));
        }
        else if (!board.first()) {
            font.draw(stage.getBatch(), "You missed.\nToo slow!", width*27/50, height - board_score.getHeight()*6/16);
        }

        // GRID AND MOLES
        stage.getBatch().draw(board_score, 0, 7*height/16, width, 3*height/16);
        stage.getBatch().draw(board_top, 0, 9*height/16, width, height/4);
        drawMole(5,9);
        stage.getBatch().draw(board_score, 0, 5*height/16, width, 2*height/16);
        stage.getBatch().draw(board_second_top, 0, 6*height/16, width, 3*height/16);
        drawMole(2,6);
        stage.getBatch().draw(board_score, 0, 2*height/16, width, 2*height/16);
        stage.getBatch().draw(board_second_bottom, 0, 3*height/16, width, 3*height/16);
        drawMole(-1, 3);
        stage.getBatch().draw(board_bottom, 0, 0 , width, 3*height/16);
        stage.getBatch().end();

    }


    public void loadTextures() {

        // Setting up local references to the already loaded textures
        moleImages.clear();
        for (int i = 0; i < 6; i++) {
            Texture image = Assets.manager.get(themePath + MoleImage.getFileNameOnImageId(i), Texture.class);
            moleImages.add(image);
        }

        board_bottom = Assets.manager.get(themePath + Assets.BOARD_BOTTOM, Texture.class);
        board_second_bottom = Assets.manager.get(themePath + Assets.BOARD_SECOND_BOTTOM, Texture.class);
        board_second_top = Assets.manager.get(themePath + Assets.BOARD_SECOND_TOP, Texture.class);
        board_top= Assets.manager.get(themePath + Assets.BOARD_TOP, Texture.class);
        board_score = Assets.manager.get(themePath + Assets.BOARD_SCORE, Texture.class);

        float var = (float)height/5;
        System.out.println(var);
        System.out.println(var/5);

        if(theme.equals(Theme.KARDASHIAN)) {
            font = Assets.manager.get(Assets.KARD_FONT_GAME);
            getReadyFont = Assets.manager.get(Assets.KARD_GET_READY_FONT);
        }
        else {
            font = Assets.manager.get(Assets.PRES_FONT_GAME);
            getReadyFont = Assets.manager.get(Assets.PRES_GET_READY_FONT);
        }
    }

    private void drawMole(int start, int end){
        Batch batch = stage.getBatch();
        if((currentMole != null) && (currentMole.getLocation() > start) && (currentMole.getLocation() < end)){
            /*
            if(currentMole.finished()){
                    currentMole.reset();
                    //board.removeCurrentMole();
            }
            */
            if(!currentMole.isHidden()){
                currentMole.update(0.015f);
                batch.draw(getMoleImage(currentMole),
                        currentMole.getPosition().x,
                        currentMole.getPosition().y, 17 * width / 60, 33*height / 160);
            }
        }
    }

    private Texture getMoleImage(Mole mole) {
        return moleImages.get(mole.getMoleImageId());
    }

    private String getLeaderBoard() {
        List<Player> scoreList = match.getSortedHighScoreList();
        String list = "";
        for(int i = 0; i < scoreList.size(); i++) {
            Player player = scoreList.get(i);
            boolean isThisPlayer = player.getNickname().equals(Match.getCurrentMatch().getThisPlayerNickName());
            if(!(i >= 3) || isThisPlayer) {
                String line = (i + 1) + ". " + player.getNickname() + ": " + player.getScore();
                if (isThisPlayer) {
                    line = (i + 1) + ". You: " + player.getScore();
                }
                if(i >= 3) {
                    list += line;
                }
                else {
                    list += line + "\n";
                }
            }
        }
        return list;
    }

}
