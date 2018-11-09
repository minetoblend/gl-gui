package com.minetoblend.parnorama.gui.draw;

import com.minetoblend.parnorama.gui.Window;
import com.minetoblend.parnorama.gui.element.Element;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    private final Window window;
    private final GLState state;

    public Renderer(Window window) {
        this.window = window;
        state = window.getGLState();
        state.setDepthTestEnabled(false);
        state.setTexture2DEnabled(true);
        state.setBlendEnabled(true);
        state.setBlendFunc(GLState.BlendFactor.SRC_ALPHA, GLState.BlendFactor.ONE_MINUS_SRC_ALPHA);

    }

    public void draw() {

        //setupMatrix();

        draw(window);
    }

    public void draw(Element e){
        e.paint();
    }

    boolean repaintAll = true;

    private void setupMatrix() {
        int width = (int) window.getWidth();
        int height = (int) window.getHeight();

        glViewport(0,0,width,height);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,width,height,0,-100,100);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }
}
