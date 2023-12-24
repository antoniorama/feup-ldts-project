package com.l08gr05.uno.controller.game;


import com.l08gr05.uno.Application;
import com.l08gr05.uno.Game.Game;

import com.l08gr05.uno.decks_cards.Card;



import java.io.IOException;
import java.util.Set;


public class CPUController extends GameController {
    private final DrawController drawController;
    private final PlayedCardController playedCardController;
    private final ColorChooserController colorChooserController;
    public CPUController(Game game){
        super(game);
        drawController = new DrawController(game);
        playedCardController = new PlayedCardController(game);
        colorChooserController = new ColorChooserController(game);
    }

/*
    Card Rating System:
            1 - Special Card/Same Color Stop or Reverse
        2 - Draw 2 Same Color
        3 - Card Same Color different Number
        4- Stop or Reverse Different
        5- Draw 2 Different Color
        6 - Same number Different Color
        7 - Draw 4
            8 - Color Changer
    If opponent has 1 card then:
            1 - Special Card/Same Color Stop or Reverse
        2 - Draw 2 Same Color
        3 - Draw 4
            4- Stop or Reverse Different
        5- Draw 2 Different Color
        6- Card Same Color different Number
        7- Same number Different Color
        8- Color Changer
*/

    private Card getNextCard() {
        int min = 55; //random value doesn't matter
        Card playableCard = null;
        for (Card card : getModel().get_cpuDeck().get_deckList()) {
            int rating;
            if (card.get_color() == getModel().get_color()) {
                if (card.get_type() == "10" || card.get_type() == "11") {
                    rating = 1;
                } else if (card.get_type() == "12") {
                    rating = 2;
                } else {
                    rating = 3;
                }
            } else if (card.get_type() == getModel().get_playedDeck().getTop().get_type()) {
                if (card.get_type() == "10" || card.get_type() == "11") {
                    rating = 4;
                } else if (card.get_type() == "12") {
                    rating = 5;
                } else {
                    rating = 6;
                }
            } else if (card.get_type() == "14") {
                rating = 7;
            } else if(card.get_type() == "13"){
                rating = 8; //just for the Color Changer
            }
            else{rating = 56;}
            if (rating < min) {
                min = rating;
                playableCard = card;
            }
        }
        return playableCard;
    }

    public void step(Application application, Set<Integer> pressedKeys) throws IOException {
        if(getModel().get_cpuDeck().size() == 1 && getNextCard() != null){
            //cpuWon
            application.setState(null);
        }
        if(getModel().get_colorChooser()){
            colorChooserController.step(application,pressedKeys);
        }
        else if(getNextCard() != null){
            playedCardController.set_playedCard(getNextCard());
            playedCardController.step(application,pressedKeys);
        }
        else{
            drawController.step(application,pressedKeys);
        }
    }
}
