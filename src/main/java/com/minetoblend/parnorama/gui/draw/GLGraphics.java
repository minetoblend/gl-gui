package com.minetoblend.parnorama.gui.draw;

import com.minetoblend.parnorama.gui.Window;
import com.minetoblend.parnorama.gui.element.Element;
import com.minetoblend.parnorama.gui.style.Color;

import java.util.Arrays;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.EXTFramebufferObject.*;


public class GLGraphics {

    private static final HashMap<Window, Shapes> shapeRegistry = new HashMap<>();
    private final Element element;
    boolean hasFbo;
    FBO fbo;
    float[] oldMatrix = new float[16];
    private Shapes shapes;

    private GLGraphics(Window window, Element e) {
        this.element = e;
        synchronized (shapeRegistry) {
            if ((shapes = shapeRegistry.get(window)) == null) {
                shapes = new Shapes();
                shapeRegistry.put(window, shapes);
            }
        }

    }

    public static GLGraphics create(Window window) {
        GLGraphics graphics = new GLGraphics(window, window);


        return graphics;
    }

    public static GLGraphics create(Window window, Element e) {
        GLGraphics graphics = new GLGraphics(window, e);
        graphics.hasFbo = true;
        return graphics;
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void clearRect(float x, float y, float width, float height) {
        throw new UnsupportedOperationException();
    }

    public void fillRect(float x, float y, float width, float height) {
        glPushMatrix();
        glTranslatef(x, y, 0);
        glScalef(width, height, 1);

        glCallList(shapes.fillRectangle());

        glPopMatrix();
    }

    public void drawRect(float x, float y, float width, float height) {
        glPushMatrix();
        glTranslatef(x, y, 0);
        glScalef(width, height, 1);

        glCallList(shapes.drawRectangle());

        glPopMatrix();
    }

    public void setColor(Color color) {
        color.bind();
    }

    public void enter() {
        if (hasFbo) {
            if (fbo == null)
                fbo = new FBO((int) element.getWidth(), (int) element.getHeight());

            glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fbo.getFbo());
        }
        glPushAttrib(GL_VIEWPORT_BIT);
        glViewport(0, 0, (int) element.getWidth(), (int) element.getHeight());
        bindTexture(0);
        glGetFloatv(GL_PROJECTION_MATRIX, oldMatrix);

        glMatrixMode(GL_PROJECTION);
        element.getMatrix().loadMatrix();
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        int m_viewport[] = new int[4];

        glGetIntegerv( GL_VIEWPORT, m_viewport );
        System.out.println(Arrays.toString(m_viewport));
    }

    public void leave() {
        if (hasFbo) {
            glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
        }
        glPopAttrib();
        glMatrixMode(GL_PROJECTION);
        glLoadMatrixf(oldMatrix);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public void resize(int width, int height) {
        if (fbo != null)
            fbo.resize(width, height);
    }

    public FBO getFbo() {
        return fbo;
    }

    public void bindTexture(int texture) {
        glBindTexture(GL_TEXTURE_2D, texture);
    }
}
