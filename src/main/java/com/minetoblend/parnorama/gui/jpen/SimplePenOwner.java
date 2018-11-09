package com.minetoblend.parnorama.gui.jpen;

import jpen.PenProvider.Constructor;
import jpen.owner.AbstractPenOwner;
import jpen.owner.PenClip;

import java.util.Arrays;
import java.util.Collection;

public class SimplePenOwner extends AbstractPenOwner {
    public SimplePenOwner(GLFWPenClip clip) {
        this.penClip = clip;
    }

    @Override
    protected void draggingOutDisengaged() {

    }

    private GLFWPenClip penClip;


    @Override
    protected void init() {
        penManagerHandle.setPenManagerPaused(false);
    }

    @Override
    public Collection<Constructor> getPenProviderConstructors() {
        return Arrays.asList(new Constructor[]{
                new jpen.provider.xinput.XinputProvider.Constructor(),
                new jpen.provider.wintab.WintabProvider.Constructor(),
                new jpen.provider.osx.CocoaProvider.Constructor()
        });
    }

    @Override
    public PenClip getPenClip() {
        return penClip;
    }

    public void setSize(int width, int height){
        this.penClip.setSize(width, height);
    }

    public void setPos(int x, int y){
        this.penClip.setPos(x,y);
    }


}
