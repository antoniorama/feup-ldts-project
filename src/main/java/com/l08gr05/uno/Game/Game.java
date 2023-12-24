package com.l08gr05.uno.Game;

import com.l08gr05.uno.decks_cards.Deck;
import com.l08gr05.uno.decks_cards.StackDeck;

import java.io.IOException;

public class Game {
    private final Deck stackDeck;
    private final Deck playedDeck;
    private final Deck playerDeck;
    private final Deck cpuDeck;
    private String color;
    private boolean colorChooser;
    private int indexColorChooser;
    private boolean playerTurn;
    private boolean playerDraw;
    public Game() throws IOException {
        stackDeck = new StackDeck();
        playedDeck = new Deck(stackDeck.drawFirst());
        color = playedDeck.getTop().get_color();
        playerDeck = new Deck(stackDeck.drawTop(7));
        cpuDeck = new Deck(stackDeck.drawTop(7));
        colorChooser = false;
        playerTurn = true;
        playerDraw = false;
    }

    public Deck get_stackDeck(){
        return stackDeck;
    }
    public Deck get_playerDeck(){
        return playerDeck;
    }
    public Deck get_cpuDeck(){
        return cpuDeck;
    }

    public Deck get_playedDeck() {
        return playedDeck;
    }
    public boolean get_playerTurn(){
        return playerTurn;
    }
    public void set_playerTurn(boolean turn) {
        playerTurn = turn;
    }

    public void set_color(String color){
        this.color = color;
    }
    public String get_color(){
//        System.out.println(color);
        return color;
    }
    public void set_colorChooser(boolean chooser){
        colorChooser = chooser;
    }
    public boolean get_colorChooser(){
        return colorChooser;
    }
    public void set_indexColorChooser(int index){
        indexColorChooser = index;
    }
    public int get_indexColorChooser(){
        return indexColorChooser;
    }
    public void set_playerDraw(boolean draw){
        playerDraw = draw;
    }
    public boolean get_playerDraw(){
        return playerDraw;
    }
}
