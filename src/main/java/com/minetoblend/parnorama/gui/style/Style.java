package com.minetoblend.parnorama.gui.style;

import com.minetoblend.parnorama.gui.element.Element;

import java.util.StringJoiner;

public class Style {

    Border border;
    private Color background;

    public Style() {

    }

    public Style(Style style) {
        this.background = style.background;
        this.border = style.border;

    }

    public static Style getDefault() {
        Style s = new Style();

        s.background = Color.WHITE;
        s.border = null;

        return s;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Style inheritFrom(Style style) {
        Style s = new Style(style);

        if (background != null)
            s.background = background;
        if (border != null)
            s.border = border;

        return s;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public boolean hasBorder() {
        if (border != null) {
            return !border.isEmpty();
        }
        return false;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Style.class.getSimpleName() + "[", "]")
                .add("border=" + border)
                .add("background=" + background)
                .toString();
    }


}
