package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameState {


    /**
     *  This is the main game logic class.
     *  This is where everything (at least a lot) happens when the game has started
     *
     *
     *  The view:
     *  The view should render and display the running game based on the GameState model.
     *  One way to update views would be to make the models Observable and the views to Observers.
     *  This way the view can be notified and rerender the view on model changes.
     *
     *  The controller:
     *  Should register clicks (mole hits), check them, and do something with the GameState based on this.
     *  TODO: it would probably be appropriate to make the controller handle communication of registered hits to the server?
     *  The controller should update the GameState (and maybe other models) on user interaction
     *
     *  The model:
     *  Game logic when playing the game
     *
     *
     *
     *
     *
     */

    private Board board = new Board();
    private GameSettings gs = new GameSettings();



}
