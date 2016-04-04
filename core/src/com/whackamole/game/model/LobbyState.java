package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class LobbyState {


    private GameSettings game_setting;
    private String name;
    private int id;
    private GameSettings.gameTheme game_theme;
    private int num_of_moles;

    public LobbyState(){
        game_setting = new GameSettings(name, id, game_theme, num_of_moles);
    }


}
