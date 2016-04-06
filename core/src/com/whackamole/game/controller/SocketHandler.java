package com.whackamole.game.controller;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class SocketHandler {

    /**
     *  Handle sockets.
     *
     *  Create new sockets and connections to the server and maintain the sockets.
     *  Also other relevant code related to the communication between the server and the client.
     *
     *  Another way to approach this class is to use it as a channel for sending messages TO the server,
     *  while using SocketListener to receive messages from the server.
     *  This might be a good way to do it since we would have one "undisturbed" channel for sending messages (clicks)
     *  to the server, while we have another channel where the server can send messages as it wants to the client.
     *  Also by seperating classes for sending/receiving data, we can put all controller code that parses messages
     *  from the server in SocketListener, and all the controller code for sending data to the server in the SocketHandler.
     *
     */


}
