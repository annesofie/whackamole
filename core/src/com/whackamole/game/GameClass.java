package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whackamole.game.model.Board;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.SocketRetreiver;
import com.whackamole.game.views.BoardRenderer;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class GameClass extends Game {


	/**
	 *
	 * 	Rendering is happening here.
	 * 	This class should probably be notified by the models when changes happen there and rerender
	 * 	based on that notification.
	 *
	 *
	 */

    SpriteBatch batch;
    Texture img;
    Socket socket;
    JsonReader reader;
    int imagex;
    int imagey;

    @Override
    public void create() {

        /**
         *
         *  RUN ON APP START
         *  USED TO SETUP EVERYTHING
         *
         *
         */



        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        imagex = 0;
        imagey = 0;

        reader = new JsonReader();
        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();
        socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println("connected to socket");
            }

        });
        socket.on("chat message", onNewMessage);
        socket.on("new mole", newMole);
    }

    @Override
    public void render() {
        Board board = new Board(Theme.KARDASHIAN);
        BoardRenderer renderer = new BoardRenderer(board, board.getMoles().get(4));
        renderer.render();
        //board.getMoles().get(6).hide();
        //MoleRenderer mr = new MoleRenderer(board.getMoles().get(6));
        //mr.render();


       /* batch.begingit add();
        batch.draw(img, imagex, imagey);
        batch.end();*/
    }

    @Override
    public void pause() {
        /**
         *  This method is run when a user exits the app.
         *  It pauses the game.
         *
         */
    }


    @Override
    public void resume() {

        /**
         *  This method is called when the app is put back into the foreground.
         *  It is run to resume the game.
         *
         */
    }

    @Override
    public void dispose() {
        /**
         *  This method is called when the application is closed (the process itself).
         *  This should be used to do some cleanup (save states, etc. if necessary)
         *
         */


    }



    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Connected error");
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String message = (String) args[0];
            String[] coord = message.split(",");
            if (coord.length < 2){
                return;
            }
            imagex = Integer.parseInt(coord[0]);
            imagey = Integer.parseInt(coord[1]);
        }
    };

    private Emitter.Listener newMole = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String message = (String) args[0];
            String[] coord = message.split(",");
            if (coord.length < 2){
                return;
            }
            imagex = Integer.parseInt(coord[0]);
            imagey = Integer.parseInt(coord[1]);
        }
    };

}
