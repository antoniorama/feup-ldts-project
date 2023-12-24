package com.l08gr05.uno.state;

import com.l08gr05.uno.Game.Menu;
import com.l08gr05.uno.controller.Controller;
import com.l08gr05.uno.controller.menu.MenuController;
import com.l08gr05.uno.viewer.Viewer;
import com.l08gr05.uno.viewer.menu.MenuViewer;

import java.io.IOException;

public class MenuState extends State<Menu> {

    public MenuState(Menu menu) throws IOException {super(menu);}

    @Override
    protected Viewer<Menu> getViewer() throws IOException {return new MenuViewer(getModel());}

    @Override
    protected Controller<Menu> getController() {return new MenuController(getModel());}
}
