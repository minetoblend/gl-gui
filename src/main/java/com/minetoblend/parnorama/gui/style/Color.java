package com.minetoblend.parnorama.gui.style;

import static org.lwjgl.opengl.GL11.*;

public class Color {


    public static final Color BLACK = new Color(0,0,0);
    public static final Color WHITE = new Color(1, 1, 1);
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    float r, g, b, a;

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(float r, float g, float b) {
        this(r, g, b, 1);
    }

    public void bind() {
        if (a >= 1) {
            glColor3f(r, g, b);
        } else {
            glColor4f(r, g, b, a);
        }
    }
}
