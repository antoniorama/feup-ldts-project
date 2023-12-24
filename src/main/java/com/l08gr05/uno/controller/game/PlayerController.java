package com.l08gr05.uno.controller.game;

import com.l08gr05.uno.Application;
import com.l08gr05.uno.Game.Game;
import com.l08gr05.uno.decks_cards.Card;

import java.awt.event.KeyEvent;
import java.util.Set;

public class PlayerController extends GameController{
    private int indexSelected;
    private final DrawController drawController;
    private final PlayedCardController playedCardController;
    private final ColorChooserController colorChooserController;
    public PlayerController(Game game){
        super(game);
        drawController = new DrawController(game);
        playedCardController = new PlayedCardController(game);
        colorChooserController = new ColorChooserController(game);
        indexSelected = 0;
        setSelectStatus(0,true);
    }
    private void setSelectStatus(int index, boolean status){
        getModel().get_playerDeck().get(index).setIsSelected(status);
    }

    private void playCard(){
        setSelectStatus(indexSelected,false);
        playedCardController.set_playedCard(getModel().get_playerDeck().get(indexSelected));
    }
    private boolean canCardBePlayed(){
        for(Card card : getModel().get_playerDeck().get_deckList()){
            if(getModel().get_playedDeck().getTop().canCardBePlayedOver(card) || getModel().get_color() == card.get_color()){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfAnySelected(){
        for(Card card:getModel().get_playerDeck().get_deckList()){
            if (card.get_isSelected()){
                return true;
            }
        }
        return false;
    }

    public void step(Application application, Set<Integer> pressedKeys) {
        if(pressedKeys != null){
            if(getModel().get_colorChooser()){
                colorChooserController.step(application,pressedKeys);
            }
            else{
                if(!checkIfAnySelected()){
                    indexSelected = 0;
                    setSelectStatus(indexSelected,true);
                }
               if(canCardBePlayed()){
                    if (pressedKeys.contains(KeyEvent.VK_RIGHT) && indexSelected != getModel().get_playerDeck().size() - 1) {
                        setSelectStatus(indexSelected, false);
                        indexSelected++;
                        setSelectStatus(indexSelected, true);
                    } else if (pressedKeys.contains(KeyEvent.VK_LEFT) && indexSelected != 0) {
                        setSelectStatus(indexSelected, false);
                        indexSelected--;
                        setSelectStatus(indexSelected, true);
                    } else if(pressedKeys.contains(KeyEvent.VK_ENTER) && (getModel().get_playedDeck().getTop().canCardBePlayedOver(getModel().get_playerDeck().get(indexSelected)) ||  getModel().get_playerDeck().get(indexSelected).get_color() == getModel().get_color())){
                        if(getModel().get_playerDeck().size() == 1){
                            application.setState(null);
                        }
                        playCard();
                        playedCardController.step(application,pressedKeys);
                    }
                    getModel().set_playerDraw(false);
                }
                else{
                    getModel().set_playerDraw(true);
                    drawController.step(application,pressedKeys);
                }
            }
        }

    }
}