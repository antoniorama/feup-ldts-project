package com.l08gr05.uno.controller.game;

import com.l08gr05.uno.Game.Game;
import com.l08gr05.uno.controller.Controller;

public abstract class GameController extends Controller<Game> {

    public GameController(Game game) {
        super(game);
    }
}
