package com.minetoblend.parnorama.gui.draw;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.EXTFramebufferObject.*;

public class FBO {

    private int width, height;
    private int fbo;
    private int texture;
    private int depthBuffer;

    public FBO(int width, int height) {
        this.width = width;
        this.height = height;
        generate();
    }

    public int getFbo() {
        return fbo;
    }

    private void generate() {
        if (fbo != 0) {
            glDeleteFramebuffersEXT(fbo);
            glDeleteTextures(texture);
            glDeleteRenderbuffersEXT(depthBuffer);
        }

        fbo = glGenFramebuffersEXT();
        texture = glGenTextures();
        depthBuffer = glGenRenderbuffersEXT();


        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fbo);
        {
            glBindTexture(GL_TEXTURE_2D, texture);
            {
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, (ByteBuffer) null);

                glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT, GL_COLOR_ATTACHMENT0_EXT, GL_TEXTURE_2D, texture, 0);
            }
            glBindTexture(GL_TEXTURE_2D, 0);


            glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, depthBuffer);
            {
                glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL_DEPTH_COMPONENT, width, height);
            }
            glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, 0);


        }
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);

    }

    public void resize(int width, int height) {

        if (width != this.width || height != this.height) {
            this.width = width;
            this.height = height;
            generate();
        }
    }


    public int getTexture() {
        return texture;
    }
}
