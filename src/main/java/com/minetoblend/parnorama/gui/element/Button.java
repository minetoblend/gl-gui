package com.minetoblend.parnorama.gui.element;

import com.minetoblend.parnorama.gui.draw.GLGraphics;
import com.minetoblend.parnorama.gui.events.MouseEvent;
import com.minetoblend.parnorama.gui.style.Border;
import com.minetoblend.parnorama.gui.style.Color;
import org.joml.Vector2f;

public class Button extends Element {
    String text;

    public Button() {
        this("");
    }

    public Button(String text) {
        this.text = text;

        setPreferredSize(new Vector2f(70, 25));
        getStyle().setBorder(new Border(1, Border.Style.SOLID, Color.BLACK));
        getStyle().setBackground(new Color(.8f, .8f, .8f));
        getHoverStyle().setBackground(new Color(.7f, .7f, .7f));
        getActiveStyle().setBackground(new Color(.5f, .5f, .5f));
    }

    @Override
    public void paint(GLGraphics g) {
        super.paint(g);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        super.mousePressed(event);
        System.out.println("Button Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        super.mouseReleased(event);
        System.out.println("Button Released");
    }

}
