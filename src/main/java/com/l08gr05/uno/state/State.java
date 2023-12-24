package com.l08gr05.uno.state;

import com.l08gr05.uno.Application;
import com.l08gr05.uno.GUI;
import com.l08gr05.uno.controller.Controller;
import com.l08gr05.uno.viewer.Viewer;
import java.io.IOException;

public abstract class State<T>{
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public  State(T model) throws IOException {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Viewer<T> getViewer() throws IOException;
    protected abstract Controller<T> getController();

    public T getModel(){return model;}

    public void step(Application application,GUI gui) throws Exception {
        gui.updateKeyStrokes();
        controller.step(application,gui.get_pressedKeys());
        viewer.draw(gui);
    }
}
