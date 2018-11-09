package com.minetoblend.parnorama.gui.element;

import com.minetoblend.parnorama.gui.draw.GLGraphics;
import com.minetoblend.parnorama.gui.style.Color;

public class CurveEditor extends Element {
    float[] values = new float[5];

    public CurveEditor() {
        resize(100, 100);
        getStyle().setBackground(new Color(0.9f, 0.9f, 0.9f));
        for (int i = 0; i < values.length; i++) {
            values[i] = i / (values.length - 1f);
        }
    }

    @Override
    public void paint(GLGraphics g) {
        super.paint(g);

        g.setColor(new Color(0.8f, 0.8f, 0.8f));
        for (int i = 0; i < values.length; i++) {
            float x = i / (values.length - 1f) * getWidth();
            float y = values[i] * getHeight();
            g.fillRect(x - 3, y - 3, 6, 6);
        }

    }
}
