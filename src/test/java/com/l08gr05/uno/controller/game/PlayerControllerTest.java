package com.l08gr05.uno.controller.game;

import com.l08gr05.uno.Application;
import com.l08gr05.uno.Game.Game;
import com.l08gr05.uno.decks_cards.Card;
import com.l08gr05.uno.decks_cards.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerControllerTest {

    private Application application;
    private Game game;
    private PlayerController playerController;
    private Set<Integer> pressedKeys;

    @BeforeEach
    public void setUp() throws Exception {
        Card.setWidthHeight(10, 10);
        game = Mockito.mock(Game.class);
        application = Mockito.mock(Application.class);
        pressedKeys = new HashSet<Integer>();

        Card yellowTwo = new Card("yellow", "02");
        Card redBlock = new Card("red", "10");
        Card plusFour = new Card("red", "07");

        // Stubs for the decks
        Deck stubPlayerDeck = new Deck(Arrays.asList(yellowTwo, redBlock, plusFour));
        Deck stubStackDeck = new Deck(Arrays.asList(redBlock, yellowTwo));
        Deck stubCpuDeck = new Deck(Arrays.asList(plusFour));
        Mockito.when(game.get_playerDeck()).thenReturn(stubPlayerDeck);
        Mockito.when(game.get_stackDeck()).thenReturn(stubStackDeck);
        Mockito.when(game.get_cpuDeck()).thenReturn(stubCpuDeck);
    }

    @Test
    public void cardSelection() throws IOException {
        // This piece of code is here instead of in setUp() because we need to call the playerController constructor after
        // creating the playedDeck stub, and the playedDeck will be different in other tests
        Card yellowThree = new Card("yellow", "03");
        Deck stubPlayedDeck = new Deck(Arrays.asList(yellowThree));
        Mockito.when(game.get_playedDeck()).thenReturn(stubPlayedDeck);

        playerController = new PlayerController(game);

        // Verifies that, initially, the first card is selected, but clicking in the right arrow key, the second one is selected
        assertTrue(game.get_playerDeck().get(0).get_isSelected());
        pressedKeys.add(KeyEvent.VK_RIGHT);
        playerController.step(application, pressedKeys);
        pressedKeys.remove(KeyEvent.VK_RIGHT);
        assertTrue(game.get_playerDeck().get(1).get_isSelected());

        // Verifies that if the last card is selected (the third one in this case), se we continue to press the right arrow key (10 times)
        // the last card is still selected
        for (int i = 0; i < 10; i++)
            pressedKeys.add(KeyEvent.VK_RIGHT);
        for (int i = 0; i < 10; i++) {
            playerController.step(application, pressedKeys);
            pressedKeys.remove(KeyEvent.VK_RIGHT);
        }
        assertTrue(game.get_playerDeck().get(2).get_isSelected());

        // Verifies that clicking the left arrow selects the previous card
        pressedKeys.add(KeyEvent.VK_LEFT);
        playerController.step(application, pressedKeys);
        pressedKeys.remove(KeyEvent.VK_LEFT);
        assertTrue(game.get_playerDeck().get(1).get_isSelected());

        // Gets back to the beginning and verifies the first one is selected after the key being pressed 10 times
        for (int i = 0; i < 15; i++)
            pressedKeys.add(KeyEvent.VK_LEFT);
        for (int i = 0; i < 15; i++) {
            playerController.step(application, pressedKeys);
            pressedKeys.remove(KeyEvent.VK_LEFT);
        }
        assertTrue(game.get_playerDeck().get(0).get_isSelected());
    }

    // None of the cards in player deck are playable
    @Test
    public void cardSelectionWhenNoneIsPlayable() throws IOException {
        Card yellowThree = new Card("green", "08");
        Deck stubPlayedDeck = new Deck(Arrays.asList(yellowThree));
        Mockito.when(game.get_playedDeck()).thenReturn(stubPlayedDeck);

        playerController = new PlayerController(game);

        // Verifies if the first one keeps selected
        assertTrue(game.get_playerDeck().get(0).get_isSelected());
        pressedKeys.add(KeyEvent.VK_RIGHT);
        playerController.step(application, pressedKeys);
        assertTrue(game.get_playerDeck().get(0).get_isSelected());
    }
}