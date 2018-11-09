package com.minetoblend.parnorama.gui.events;

public class MouseEvent {
    public static final int PRESS = 1;
    public static final int RELEASE = 2;
    public static final int MOVE = 3;
    public static final int DRAG = 4;
    public static final int ENTERED = 5;
    public static final int LEFT = 6;


    public int button;
    public int mods;
    public int action;
    public float x, y;
    public float dX, dY;
    public float globalX;
    public float globalY;

    public MouseEvent(int button, int mods, int action, float x, float y, float dX, float dY, float globalX, float globalY) {
        this.button = button;
        this.mods = mods;
        this.x = x;
        this.y = y;
        this.dX = dX;
        this.dY = dY;
        this.globalX = globalX;
        this.globalY = globalY;
        this.action = action;
    }

    public MouseEvent translate(float dx, float dy) {
        return new MouseEvent(button, mods, action, x + dx, y + dy, dX, dY, globalX, globalY);
    }
}
