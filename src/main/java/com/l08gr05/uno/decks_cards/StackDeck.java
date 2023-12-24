package com.l08gr05.uno.decks_cards;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StackDeck extends Deck{
    public StackDeck() throws IOException {
        List<String> coloredTypeList = Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12");
        List<String> blackTypeList = Arrays.asList("13", "14");
        List<String> colorsList =  Arrays.asList("red", "blue", "yellow", "green");

        for(String color:colorsList){
            //0s are created apart because there's only one instance of each of them
            deckList.add(new Card(color,"00"));
            for(String type : coloredTypeList){
                deckList.add(new Card(color,type));
                deckList.add(new Card(color,type));
            }
        }

        for(String type : blackTypeList){
            deckList.add(new Card(type));
            deckList.add(new Card(type));
            deckList.add(new Card(type));
            deckList.add(new Card(type));
        }

        Collections.shuffle(deckList);
    }
}
