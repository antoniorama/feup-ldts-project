package com.l08gr05.uno.controller.menu;

import com.l08gr05.uno.Application;
import com.l08gr05.uno.Game.Game;
import com.l08gr05.uno.Game.Menu;
import com.l08gr05.uno.controller.Controller;
import com.l08gr05.uno.state.GameState;


import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu menu) {super(menu); }

    @Override
    public void step(Application application, Set<Integer> pressedKeys) throws IOException {
        if (pressedKeys != null) {
            if (pressedKeys.contains(KeyEvent.VK_UP))
                getModel().previousEntry();
            else if (pressedKeys.contains(KeyEvent.VK_DOWN))
                getModel().nextEntry();
            else if (pressedKeys.contains(KeyEvent.VK_ESCAPE))
                application.setState(null);
            else if(pressedKeys.contains(KeyEvent.VK_ENTER)){
                if(getModel().isSelectedJogar()){
                    System.out.println("Loading...");
                    application.setState(new GameState(new Game()));
                }
                else{
                    application.setState(null);
                }
            }
        }
    }
}
