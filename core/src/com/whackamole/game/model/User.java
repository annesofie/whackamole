package com.whackamole.game.model;

import java.util.ArrayList;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class User {

    String username;
    int userId;
    int points;

    boolean loggedIn = false;
    public static ArrayList<User> userList = new ArrayList<User>();
    public static ArrayList<Integer> idList = new ArrayList<Integer>();
    public static ArrayList<String> namelist = new ArrayList<String>();

    public User(String username){
        this.username = setUsername(username);
        this.userId = createId();
        this.loggedIn = true;
        userList.add(this);
        idList.add(this.userId);
        namelist.add(this.username);
        //probably unnecessary
        this.loggedIn = true;
        
    }

    private int createId(){
        if(idList.size() > 0) {
            return idList.get(idList.size() - 1) + 1;
        }
        return 0;
    }

    public String setUsername(String username){
        for (String s: namelist) {
            if(s.equalsIgnoreCase(username)){
                throw new IllegalArgumentException("Username is already taken.");
            }
        }
        return username;
    }


    public void logout(){
        int temp = userList.indexOf(this);
        userList.remove(this);
        synchronized (idList) {
            if (idList.get(temp) == this.userId) {
                idList.remove(temp);
            }
        }
        namelist.remove(this.username);
        //probably unnecessary
        this.loggedIn = false;
    }

    public String getName(){
        return this.username;
    }

    public int getId(){
        return this.userId;
    }

    
}
