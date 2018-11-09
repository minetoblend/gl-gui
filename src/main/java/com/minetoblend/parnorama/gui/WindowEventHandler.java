package com.minetoblend.parnorama.gui;

import com.minetoblend.parnorama.gui.interfaces.GLFWWindowEventHandler;
import com.minetoblend.parnorama.gui.jpen.JPenHandler;
import com.sun.istack.internal.NotNull;

public class WindowEventHandler implements GLFWWindowEventHandler {

    private final @NotNull
    Window window;


    private final InputHandler inputHandler;
    int windowWidth = 1280;
    int windowHeight = 720;
    private JPenHandler jPenHandler;
    public WindowEventHandler(Window window) {
        System.out.println(window);
        this.window = window;
        jPenHandler = new JPenHandler();
        inputHandler = new InputHandler(this);
    }

    @Override
    public void resized(long window, int width, int height) {
        jPenHandler.getPenOwner().setSize(width, height);
        windowWidth = width;
        windowHeight = height;
        this.window.resize(width, height);
    }

    @Override
    public void moved(long window, int x, int y) {
        jPenHandler.getPenOwner().setPos(x, y);
    }

    @Override
    public void close(long window) {

    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public Window getWindow() {
        System.out.println(window);
        return window;
    }
}
