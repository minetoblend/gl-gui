package com.minetoblend.parnorama.gui.interfaces;

public interface GLFWWindowEventHandler {


    default void resized(long window, int width, int height) {
    }

    default void moved(long window, int x, int y) {
    }

    default void close(long window) {
    }

}
