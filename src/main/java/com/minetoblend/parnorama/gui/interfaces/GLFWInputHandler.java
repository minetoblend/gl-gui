package com.minetoblend.parnorama.gui.interfaces;

public interface GLFWInputHandler {

    default void keyEvent(long window, int key, int scancode, int action, int mods){}

    default void scrollEvent(long window, double xoffset, double yoffset){}

    default void mouseButtonEvent(long window, int button, int action, int mods){}

    default void mouseMoveEvent(long window, double x, double y){}
}
