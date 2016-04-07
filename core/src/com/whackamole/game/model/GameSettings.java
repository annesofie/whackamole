package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameSettings {

    /**
     *  Should store the current game configurations to be used
     *  when running the game.
     *
     *  This should probably contain a Theme object and
     *  other configurations. The idea is that we need
     *  some way to set variables with paths to pictures
     *  , sounds, etc. that belongs to a player selected
     *  theme.
     *
     *
     *
     *  The view:
     *  Should show a list of available themes.
     *  Maybe include some thumbnails or something and a
     *  short description.
     *
     *  The controller:
     *  Multiple controllers may be used.
     *  The player click should be registered and an appropriate action should be done.
     *  In this case the controller should set appropriate configurations in the GameSettings model.
     *  The controller should also choose what state/view to be shown next after the player selected
     *  the theme. E.g. the user clicks on a theme in the list, and the user gets some kind of feedback
     *  in the view that the theme was successfully selected.
     *
     *  The model:
     *  In this case this is the model. It should contain configurations that are used by the game logic
     *  at runtime. E.g. paths to pictures and sounds, difficulty, number of players to play against, sound on/off, etc.
     *
     *
     *  Any other smart way to structure and handle
     *  configurations in our game other than having
     *  "Theme" objects etc.?
     *  Could we handle this only by setting values
     *
     *
     */


}
