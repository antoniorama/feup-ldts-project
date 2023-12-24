package com.l08gr05.uno.controller.game;


import com.l08gr05.uno.Application;
import com.l08gr05.uno.Game.Game;
import com.l08gr05.uno.decks_cards.Card;


import java.util.Set;

public class PlayedCardController extends GameController{
    private Card playedCard;

    public PlayedCardController(Game game){
        super(game);
        playedCard = null;
    }
    public void set_playedCard(Card card){
        playedCard = card;
    }

    private void stopReverse(){
        if(getModel().get_playerTurn()){
            getModel().get_playerDeck().remove(playedCard);
        }
        else{
            getModel().get_cpuDeck().remove(playedCard);
        }
        getModel().get_playedDeck().addTop(playedCard);
        getModel().set_color(playedCard.get_color());
    }

    private void normal(){
        stopReverse();
        getModel().set_playerTurn(!getModel().get_playerTurn());
    }

    private void draw2(){
        if(getModel().get_playerTurn()){
            getModel().get_cpuDeck().addTop(getModel().get_stackDeck().drawTop(2));
        }
        else{
            getModel().get_playerDeck().addTop(getModel().get_stackDeck().drawTop(2));
        }
        normal();
    }
    private void draw4(){
        if(getModel().get_playerTurn()){
            getModel().get_playerDeck().remove(playedCard);
            getModel().get_cpuDeck().addTop(getModel().get_stackDeck().drawTop(4));
        }
        else{
            getModel().get_cpuDeck().remove(playedCard);
            getModel().get_playerDeck().addTop(getModel().get_stackDeck().drawTop(4));
        }
        getModel().set_indexColorChooser(0);
        getModel().get_playedDeck().addTop(playedCard);
        getModel().set_colorChooser(true);
        getModel().set_indexColorChooser(0);
    }
    public void colorChanger(){
        if(getModel().get_playerTurn()){
            getModel().get_playerDeck().remove(playedCard);
        }
        else{
            getModel().get_cpuDeck().remove(playedCard);
        }
        getModel().set_indexColorChooser(0);
        getModel().get_playedDeck().addTop(playedCard);
        getModel().set_colorChooser(true);
        getModel().set_indexColorChooser(0);
    }

    private void restack(){
        getModel().get_stackDeck().addBottom(getModel().get_playedDeck().drawFromBottom(getModel().get_playedDeck().size() - 1));
    }

    public void step(Application application,  Set<Integer> pressedKeys){
        if(playedCard.isNumber()){
            normal();
        }
        else if(playedCard.get_type() == "14"){
            draw4();
        }
        else if(playedCard.get_type() == "13"){
            colorChanger();
        }
        else if(playedCard.get_type() == "12"){
            draw2();
        }
        else{
            stopReverse();
        }
        if(getModel().get_stackDeck().size() <= 4){
            restack();
        }
    }
}
