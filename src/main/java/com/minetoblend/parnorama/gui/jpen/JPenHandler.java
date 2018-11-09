package com.minetoblend.parnorama.gui.jpen;

import jpen.*;
import jpen.event.PenListener;

public class JPenHandler implements PenListener {

    private Pen pen;

    private final SimplePenOwner penOwner;
    private final PenManager manager;

    public JPenHandler(){
        penOwner = new SimplePenOwner(new GLFWPenClip());
        manager = new PenManager(penOwner);
        manager.pen.addListener(this);
    }

    @Override
    public void penKindEvent(PKindEvent pKindEvent) {

    }

    @Override
    public void penLevelEvent(PLevelEvent ev) {
        if (pen == null) pen = ev.pen;
    }

    @Override
    public void penButtonEvent(PButtonEvent pButtonEvent) {

    }

    @Override
    public void penScrollEvent(PScrollEvent pScrollEvent) {

    }

    @Override
    public void penTock(long availableMillis) {
        if (pen == null) return;

        for (PButton.Type buttonType : PButton.Type.VALUES) {
            if (pen.getButtonValue(buttonType)) {
                //System.out.println("buttonType = " + buttonType);
            }
        }

        for (PLevel.Type levelType : PLevel.Type.VALUES) {
            float value = pen.getLevelValue(levelType);
            //System.out.println("levelType = " + levelType + ", value = " + value);
        }

        pen = null;
    }

    public Pen getPen() {
        return pen;
    }

    public SimplePenOwner getPenOwner() {
        return penOwner;
    }
}
