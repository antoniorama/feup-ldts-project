package com.l08gr05.uno.controller.game;


import com.l08gr05.uno.Application;
import com.l08gr05.uno.Game.Game;

import java.awt.event.KeyEvent;
import java.io.IOException;

import java.util.Set;

public class FlowController extends GameController{
    private final PlayerController playerController;
    private final CPUController cpuController;

    public FlowController(Game game){
        super(game);
        playerController =  new PlayerController(game);
        cpuController = new CPUController(game);
    }

    private GameController getController(){
        if(getModel().get_playerTurn()){
            return playerController;
        }
        return cpuController;
    }
    @Override
    public void step(Application application,  Set<Integer> pressedKeys) throws IOException {
        if(pressedKeys != null && pressedKeys.contains(KeyEvent.VK_ESCAPE)){
            application.setState(null);
        }
        getController().step(application,pressedKeys);
    }
}
