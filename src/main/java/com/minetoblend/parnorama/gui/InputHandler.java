package com.minetoblend.parnorama.gui;

import com.minetoblend.parnorama.gui.element.Element;
import com.minetoblend.parnorama.gui.events.MouseEvent;
import com.minetoblend.parnorama.gui.interfaces.GLFWInputHandler;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler implements GLFWInputHandler {
    private final WindowEventHandler eventHandler;
    private final Window window;
    private final boolean buttonPressed[] = new boolean[5];
    private final int buttonMods[] = new int[buttonPressed.length];
    private final boolean keyPressed[] = new boolean[500];
    Element currentlyHovering;
    private float mouseX, mouseY;
    private float mouseLastX, mouseLastY;

    public InputHandler(WindowEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.window = eventHandler.getWindow();
    }

    @Override
    public void mouseButtonEvent(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            mousePressed(button, mods);
        } else if (action == GLFW_RELEASE) {
            mouseReleased(button, mods);
        }

    }

    @Override
    public void mouseMoveEvent(long window, double x, double y) {
        mouseLastX = mouseX;
        mouseLastY = mouseY;
        this.mouseX = (float) x;
        this.mouseY = (float) y;


        for (int button = 0; button < buttonPressed.length; button++) {
            if (buttonPressed[button]) {
                mouseDragged(button, buttonMods[button]);
                return;
            }
        }
        mouseMoved();
    }

    @Override
    public void keyEvent(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            keyPressed(key, scancode, mods);
        } else if (action == GLFW_RELEASE) {
            keyReleased(key, scancode, mods);
        }
    }

    @Override
    public void scrollEvent(long l, double xOffset, double yOffset) {

    }

    public boolean isButtonDown(int button) {
        if (button < 0 || button >= buttonPressed.length)
            throw new IndexOutOfBoundsException("button = " + button);
        return buttonPressed[button];
    }

    public boolean isKeyDown(int key) {
        if (key < 0 || key >= buttonPressed.length)
            throw new IndexOutOfBoundsException("button = " + key);
        return keyPressed[key];
    }

    private void mousePressed(int button, int mods) {
        buttonPressed[button] = true;
        buttonMods[button] = mods;
        MouseEvent event = new MouseEvent(button, mods, MouseEvent.PRESS, mouseX, mouseY, mouseX - mouseLastX, mouseY - mouseLastY, mouseX, mouseY);
        window.passMouseEvent(event);
    }

    private void mouseReleased(int button, int mods) {
        buttonPressed[button] = false;
        buttonMods[button] = 0;
        MouseEvent event = new MouseEvent(button, mods, MouseEvent.RELEASE, mouseX, mouseY, mouseX - mouseLastX, mouseY - mouseLastY, mouseX, mouseY);
        window.passMouseEvent(event);

    }

    private void keyPressed(int key, int scancode, int mods) {
        keyPressed[key] = true;
    }

    private void keyReleased(int key, int scancode, int mods) {
        keyPressed[key] = true;
    }

    private void mouseDragged(int button, int mods) {
        MouseEvent event = new MouseEvent(button, mods, MouseEvent.DRAG, mouseX, mouseY, mouseX - mouseLastX, mouseY - mouseLastY, mouseX, mouseY);
        window.passMouseEvent(event);
    }

    private void mouseMoved() {
        MouseEvent event = new MouseEvent(-1, 0, MouseEvent.MOVE, mouseX, mouseY, mouseX - mouseLastX, mouseY - mouseLastY, mouseX, mouseY);
        Element e = window.passMouseEvent(event);
        if (e != currentlyHovering) {

            if (currentlyHovering != null) {
                currentlyHovering.setHover(false);
                currentlyHovering.mouseLeave(new MouseEvent(-1, 0, MouseEvent.LEFT, mouseX, mouseY, mouseX - mouseLastX, mouseY - mouseLastY, mouseX, mouseY));
            }
            if (e != null) {
                e.mouseEnter(new MouseEvent(-1, 0, MouseEvent.ENTERED, mouseX, mouseY, mouseX - mouseLastX, mouseY - mouseLastY, mouseX, mouseY));
                e.setHover(true);
            }
        }
        currentlyHovering = e;
    }
}