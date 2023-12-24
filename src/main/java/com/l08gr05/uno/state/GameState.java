package com.l08gr05.uno.state;

import com.l08gr05.uno.Game.Game;
import com.l08gr05.uno.controller.Controller;
import com.l08gr05.uno.controller.game.FlowController;

import com.l08gr05.uno.viewer.Viewer;
import com.l08gr05.uno.viewer.game.GameViewer;

import java.io.IOException;

public class GameState extends State<Game>{

    public GameState (Game game) throws IOException {
        super(game);
    }


    @Override protected Viewer<Game> getViewer() throws IOException {return new GameViewer(getModel());}

    @Override
    protected Controller<Game> getController(){
        return new FlowController(getModel());
    }
}
