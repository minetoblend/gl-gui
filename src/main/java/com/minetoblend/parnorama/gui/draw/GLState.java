package com.minetoblend.parnorama.gui.draw;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;

public class GLState {



    boolean texture2DEnabled;
    boolean blendEnabled;
    boolean depthTestEnabled;
    BlendFactor blendSFactor = BlendFactor.ONE;
    BlendFactor blendDFactor = BlendFactor.ZERO;


    public boolean isTexture2DEnabled() {
        return texture2DEnabled;
    }

    public void setTexture2DEnabled(boolean texture2DEnabled) {
        this.texture2DEnabled = texture2DEnabled;
        if(texture2DEnabled)
            glEnable(GL_TEXTURE_2D);
        else
            glDisable(GL_TEXTURE_2D);
    }

    public boolean isBlendEnabled() {
        return blendEnabled;
    }

    public void setBlendEnabled(boolean blendEnabled) {
        this.blendEnabled = blendEnabled;
        if(texture2DEnabled)
            glEnable(GL_BLEND);
        else
            glDisable(GL_BLEND);
    }

    public void setBlendFunc(BlendFactor sfactor, BlendFactor dfactor){
        this.blendSFactor = sfactor;
        this.blendDFactor = dfactor;
    }

    public void setDepthTestEnabled(boolean depthTestEnabled) {
        this.depthTestEnabled = depthTestEnabled;
        if(depthTestEnabled)
            glEnable(GL_DEPTH_TEST);
        else
            glDisable(GL_DEPTH_TEST);
    }

    public boolean isDepthTestEnabled() {
        return depthTestEnabled;
    }

    public BlendFactor getBlendFuncSFactor(){
        return blendSFactor;
    }

    public BlendFactor getBlendFuncDFactor(){
        return blendDFactor;
    }

    public enum BlendFactor{
        ZERO(GL_ZERO),
        ONE(GL_ONE),
        SRC_COLOR(GL_SRC_COLOR),
        ONE_MINUS_SRC_COLOR(GL_ONE_MINUS_SRC_COLOR),
        DST_COLOR(GL_DST_COLOR),
        ONE_MINUS_DST_COLOR(GL_ONE_MINUS_DST_COLOR),
        SRC_ALPHA(GL_SRC_ALPHA),
        ONE_MINUS_SRC_ALPHA(GL_ONE_MINUS_SRC_ALPHA),
        DST_ALPHA(GL_DST_ALPHA),
        ONE_MINUS_DST_ALPHA(GL_ONE_MINUS_DST_ALPHA),
        CONSTANT_COLOR(GL_CONSTANT_COLOR),
        ONE_MINUS_CONSTANT_COLOR(GL_ONE_MINUS_CONSTANT_COLOR),
        CONSTANT_ALPHA(GL_CONSTANT_ALPHA),
        ONE_MINUS_CONSTANT_ALPHA(GL_ONE_MINUS_CONSTANT_ALPHA);

        int value;
        BlendFactor(int value){this.value = value;}
    }


}
