package com.l08gr05.uno.decks_cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {
    protected List<Card> deckList;

    public Deck(){
        deckList = new ArrayList<>();
    }
    public Deck(List<Card> cardList){
        deckList = new ArrayList<>(cardList);
    }
    public Deck(Card card){deckList = new ArrayList<>(Arrays.asList(card));}

    public void addTop(Card addedCard){
        deckList.add(addedCard);
    }
    public void addTop(List<Card> addedListCards){
        deckList.addAll(addedListCards);
    }

    //to be used for when restacking
    public void addBottom(List<Card> listCards){
        for(Card card:listCards){
            deckList.add(0,card);
        }
    }

    public void remove(Card card){
        deckList.remove(card);
    }
    public List<Card> drawFromBottom(int n){
        List<Card> ret = new ArrayList<>();
        for(int i = 1; i <= n;i++){
            ret.add(deckList.remove(0));
        }
        return ret;
    }
    public Card getTop(){
        return deckList.get(deckList.size() - 1);
    }
    public Card drawTop(){
        Card drawnCard = deckList.get(deckList.size() - 1) ;
        deckList.remove(drawnCard);
        return drawnCard;
    }
    public List<Card> drawTop(int n){
        List<Card> rList = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            rList.add(drawTop());
        }
        return rList;
    }

    public Card get(int index){
        return deckList.get(index);
    }

    public Card drawFirst(){
        for(int i = 0;i<=deckList.size();i++){
            if(deckList.get(i).isNumber()){
                return deckList.remove(i);
            }
        }
        return null;
    }
    public int size(){
        return deckList.size();
    }

    public List<Card> get_deckList(){
        return deckList;
    }

}
