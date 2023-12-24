package com.l08gr05.uno.controller.game;

import com.l08gr05.uno.Application;
import com.l08gr05.uno.Game.Game;
import com.l08gr05.uno.decks_cards.Card;

import java.awt.event.KeyEvent;
import java.util.*;

public class ColorChooserController extends GameController{
    public ColorChooserController(Game game){
        super(game);
    }

    private void playerColorChooser( Set<Integer> pressedKeys){
        if(pressedKeys.contains(KeyEvent.VK_LEFT) && getModel().get_indexColorChooser() > 0){
            getModel().set_indexColorChooser(getModel().get_indexColorChooser() - 1);
        }
        else if(pressedKeys.contains(KeyEvent.VK_RIGHT) && getModel().get_indexColorChooser() < 3){
            getModel().set_indexColorChooser(getModel().get_indexColorChooser() + 1);
        }
        else if(pressedKeys.contains(KeyEvent.VK_ENTER)){
            switch(getModel().get_indexColorChooser()){
                case 0:
                    getModel().set_color("red");
                    break;
                case 1:
                    getModel().set_color("green");
                    break;
                case 2:
                    getModel().set_color("blue");
                    break;
                case 3:
                    getModel().set_color("yellow");
            }
            getModel().set_colorChooser(false);
            getModel().set_playerTurn(false);
        }
    }

    private void cpuColorChooser(){
        String chosenColor = "blue"; //if it doesn't matter aka last card for example, it will set to standard blue because who doesn't love blue
        int numRed = 0;
        int numGreen = 0;
        int numBlue = 0;
        int numYellow = 0;
        Map<String,Integer> colors = new HashMap<>();
        for(Card card:getModel().get_cpuDeck().get_deckList()){
            switch(card.get_color()){
                case "red":
                    numRed++;
                    break;
                case "green":
                    numGreen++;
                    break;
                case "blue":
                    numBlue++;
                    break;
                case "yellow":
                    numYellow++;
            }
        }
        colors.put("red",numRed);
        colors.put("green",numGreen);
        colors.put("blue",numBlue);
        colors.put("yellow",numYellow);
        int max = 0;
        for(Map.Entry<String,Integer> entry : colors.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
                chosenColor = entry.getKey();
            }
        }
        getModel().set_color(chosenColor);
        getModel().set_colorChooser(false);
        getModel().set_playerTurn(true);
    }

    public void step(Application application,  Set<Integer> pressedKeys){
        if(getModel().get_playerTurn()) {
            playerColorChooser(pressedKeys);
        }
        else{
            cpuColorChooser();
        }
    }
}
