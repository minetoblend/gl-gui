package com.minetoblend.parnorama.gui.interfaces;

import com.minetoblend.parnorama.gui.events.MouseEvent;

public interface InputListener {

    default void mouseEvent(MouseEvent event) {
        if (event.action == MouseEvent.MOVE)
            mouseMoved(event);
        else if (event.action == MouseEvent.DRAG)
            mouseDragged(event);
        else if (event.action == MouseEvent.PRESS)
            mousePressed(event);
        else if (event.action == MouseEvent.RELEASE)
            mouseReleased(event);
        
    }

    default void mousePressed(MouseEvent event) {

    }

    default void mouseReleased(MouseEvent event) {

    }

    default void mouseMoved(MouseEvent event) {

    }

    default void mouseDragged(MouseEvent event) {

    }

    default void mouseLeave(MouseEvent event) {
    }

    default void mouseEnter(MouseEvent event) {
    }
}
