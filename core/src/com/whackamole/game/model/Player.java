package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class Player implements Comparable {

    String username;
    int score;
    boolean ready;

    public Player(String username){
        this.username = username;
        this.score = 0;
        this.ready = false;
    }

    public String getNickname() {
        return this.username;
    }

    public void setNickName(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void setReady(boolean isReady) {
        this.ready = isReady;
    }

    public boolean isReady() {
        return ready;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getScore() == ((Player)o).getScore()){
            return 0;
        }
        else if ((this.getScore()) > ((Player)o).getScore()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
