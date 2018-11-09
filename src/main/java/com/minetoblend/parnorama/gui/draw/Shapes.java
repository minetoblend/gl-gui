package com.minetoblend.parnorama.gui.draw;

import static org.lwjgl.opengl.GL11.*;

public class Shapes {
    private int fillRectangle;
    private int drawRectangle;
    private int fillCircle;
    private int drawCirlce;
    private int fillQuarterCircle;
    private int drawQuarterCircle;

    public int fillRectangle(){
        if (fillRectangle == 0) {
            fillRectangle = glGenLists(1);
            glNewList(fillRectangle, GL_COMPILE);
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0,0);
                glVertex2f(0,1);

                glTexCoord2f(1,0);
                glVertex2f(1,1);

                glTexCoord2f(1,1);
                glVertex2f(1,0);

                glTexCoord2f(0,1);
                glVertex2f(0,0);
            }
            glEnd();
            glEndList();
        }
        return fillRectangle;
    }
    
    public int drawRectangle(){
        if (drawRectangle == 0) {
            drawRectangle = glGenLists(1);
            glNewList(drawRectangle, GL_COMPILE);
            glBegin(GL_LINE_STRIP);
            {
                glTexCoord2f(0,0);
                glVertex2f(0,0);

                glTexCoord2f(1,0);
                glVertex2f(1,0);

                glTexCoord2f(1,1);
                glVertex2f(1,1);

                glTexCoord2f(0,1);
                glVertex2f(0,1);

                glTexCoord2f(0,0);
                glVertex2f(0,0);
            }
            glEnd();
            glEndList();
        }
        return drawRectangle;
    }

    public int fillOval(){
        if (fillCircle == 0) {
            fillCircle = glGenLists(1);
            glNewList(fillCircle, GL_COMPILE);
            glBegin(GL_TRIANGLE_FAN);
            {

            }
            glEnd();
            glEndList();
        }
        return fillCircle;
    }

    public int drawCirlce(){
        if (drawCirlce == 0) {
            drawCirlce = glGenLists(1);
            glNewList(drawCirlce, GL_COMPILE);
            glBegin(GL_LINE_STRIP);
            {

            }
            glEnd();
            glEndList();
        }
        return drawCirlce;
    }

    public int fillQuarterCircle(){
        if (fillQuarterCircle == 0) {
            fillQuarterCircle = glGenLists(1);
            glNewList(fillQuarterCircle, GL_COMPILE);
            glBegin(GL_TRIANGLE_FAN);
            {

            }
            glEnd();
            glEndList();
        }
        return fillQuarterCircle;
    }

    public int drawQuarterCircle(){
        if (drawQuarterCircle == 0) {
            drawQuarterCircle = glGenLists(1);
            glNewList(drawQuarterCircle, GL_COMPILE);
            glBegin(fillRectangle);
            {

            }
            glEnd();
            glEndList();
        }
        return drawQuarterCircle;
    }
}
