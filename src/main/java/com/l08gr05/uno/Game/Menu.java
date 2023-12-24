package com.l08gr05.uno.Game;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<String> entries;
    private int currentEntry = 0;

    public Menu() {this.entries = Arrays.asList("/UI/JOGAR", "/UI/SAIR");}

    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() -1;
    }

    public boolean isSelected(int i) {return currentEntry == i;}

    public boolean isSelectedJogar() {return isSelected(0);}
}
