package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class MainMenuState {

    /**
     *
     *  This model should typically contain information and some logic on what should be shown
     *  when you first start the game. Think "main menu screen".
     *
     *
     *  The view:
     *  The view should be a starter screen with a menu. E.g. "Start new game", "Join game", "Settings" and "Highscores".
     *
     *  The controller:
     *  Should register clicks and do appropriate actions.
     *  In this case the controller should register when the player clicks any of the menu items and select
     *  which model the view should display next. E.g. the player clicks "Settings" and the controller changes
     *  the view to the Settings screen based on the "GameSettings" model.
     *
     *  The model:
     *  This is the model class in this case. It should contain logic and data about the lobby state.
     *
     *
     */
    private GameSettings game_setting;
    private String name;
    private int id;
    private GameSettings.gameTheme game_theme;
    private int num_of_moles;


}
