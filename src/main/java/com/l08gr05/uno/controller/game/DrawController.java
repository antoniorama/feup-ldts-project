package com.l08gr05.uno.controller.game;


import com.l08gr05.uno.Application;
import com.l08gr05.uno.Game.Game;

import java.awt.event.KeyEvent;
import java.util.Set;

public class DrawController extends GameController{
    public DrawController(Game game){
        super(game);
    }
    private void cpuDraw(){
        getModel().get_cpuDeck().addTop(getModel().get_stackDeck().drawTop());
    }
    private void playerDraw(){
        getModel().get_playerDeck().addTop(getModel().get_stackDeck().drawTop());
    }
    private void restack(){
        getModel().get_stackDeck().addBottom(getModel().get_playedDeck().drawFromBottom(getModel().get_playedDeck().size() - 1));
    }
    public void step(Application application, Set<Integer> pressedKeys){
        if(getModel().get_stackDeck().size() <= 4){
            restack();
        }
        if(getModel().get_playerTurn()){
            if(pressedKeys.contains(KeyEvent.VK_ENTER)){
                playerDraw();
            }
        }
        else{
            cpuDraw();
        }
    }
}
