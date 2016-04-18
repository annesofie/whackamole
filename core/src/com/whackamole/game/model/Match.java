package com.whackamole.game.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lars on 15/04/16.
 */
public class Match {

    private Player thisPlayer; // The user that created the match
    private List<Player> playerList;
    private String gameName;


    public Match() {
        thisPlayer = new Player(null);
        playerList = new ArrayList<Player>();
        playerList.add(thisPlayer);
        gameName = null;
    }


    public void addPlayer(String username) {
        playerList.add(new Player(username));
    }


    public int getScoreOnUserName(String username) {
        /**
         * Returns -1 if the user with username doesn't exist
         */
        for(Player user : playerList) {
            if(user.getNickname().equals(username)) {
                return user.getScore();
            }
        }
        return -1;
    }

    public boolean setScoreToUser(String username, int totalScore) {
        /**
         *  Returns false if the username was not found
         */
        for(Player user : playerList) {
            if(user.getNickname().equals(username)) {
                user.setScore(totalScore);
                return true;
            }
        }
        return false;
    }

    public List<Player> getSortedHighScoreList() {
        Collections.sort(playerList);
        return playerList;
    }

    public void setNickNameOnThisPlayer(String nickName) {
        thisPlayer.setNickName(nickName);
    }

    public void setThisPlayerReady() {
        this.thisPlayer.setReady(true);
    }

    public void setPlayerReady(String nickname) {
        getPlayerOnNickName(nickname).setReady(true);
    }

    public List<String> getCurrentNickNames() {
        List<String> list = new ArrayList<String>();
        for (Player user : playerList) {
            list.add(user.getNickname());
        }
        return list;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return this.gameName;
    }

    public String getThisPlayerNickName() {
        return thisPlayer.getNickname();
    }

    public int numOfReadyPlayers() {
        int numOfReadyPlayers = 0;
        for(Player player : playerList) {
            if(player.isReady()){
                numOfReadyPlayers++;
            }
        }
        return numOfReadyPlayers;
    }

    private Player getPlayerOnNickName(String nickName) {
        for(Player player : playerList) {
            if(player.getNickname().equals(nickName)) {
                return player;
            }
        }
        return null;
    }

    private int getThisPlayerScore(){
        return this.thisPlayer.getScore();
    }

}
