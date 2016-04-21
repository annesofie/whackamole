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
    private int numOfPlayers;
    private static Match currentMatch;


    private Match() {
        thisPlayer = new Player(null);
        playerList = new ArrayList<Player>();
        playerList.add(thisPlayer);
        gameName = null;
        numOfPlayers = 0;
    }

    public static void startNewMatch() {
        currentMatch = new Match();
    }

    public static Match getCurrentMatch() {
        return currentMatch;
    }

    public void addPlayer(String nickName) {
        if(!(this.isPlayer(nickName))) {
            this.playerList.add(new Player(nickName));
        }
    }

    public int getScoreOnUserName(String nickName) {
        /**
         * Returns -1 if the user with username doesn't exist
         */
        for(Player player : this.playerList) {
            if(player.getNickname().equals(nickName)) {
                return player.getScore();
            }
        }
        return -1;
    }

    public boolean setScoreToUser(String nickName, int totalScore) {
        /**
         *  Returns false if the username was not found
         */
        for(Player player : this.playerList) {
            if(player.getNickname().equals(nickName)) {
                player.setScore(totalScore);
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
        Player player = getPlayerOnNickName(nickname);
        if(!(player == null))
            player.setReady(true);
    }

    public List<String> getCurrentNickNames() {
        List<String> list = new ArrayList<String>();
        for (Player player : playerList) {
            list.add(player.getNickname());
        }
        return list;
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

    private boolean isPlayer(String nickName) {
        for(Player player : playerList) {
            if(player != null && nickName != null) {
                if(player.getNickname().equals(nickName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int num) {
        numOfPlayers = num;

    }

    public List<Player> getPlayerList() {
        return playerList;
    }



    private int getThisPlayerScore() {
        return this.thisPlayer.getScore();
    }

}
