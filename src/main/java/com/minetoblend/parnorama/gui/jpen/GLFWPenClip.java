package com.minetoblend.parnorama.gui.jpen;

import com.minetoblend.parnorama.gui.Window;
import jpen.owner.PenClip;

import java.awt.*;
import java.awt.geom.Point2D;

public class GLFWPenClip implements PenClip {

    private int x, y, w, h;

    public GLFWPenClip() {
    }

    @Override
    public void evalLocationOnScreen(Point point) {

    }

    @Override
    public boolean contains(Point2D.Float aFloat) {

        return aFloat.x > x && aFloat.y > y && aFloat.x <= x + w && aFloat.y <= y + h;
    }

    public void setSize(int width, int height) {
        this.w = width;
        this.h = height;

    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
