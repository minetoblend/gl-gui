package com.minetoblend.parnorama.gui.style;

import com.minetoblend.parnorama.gui.draw.GLGraphics;
import com.sun.istack.internal.NotNull;

public class Border {

    float topWidth = 0;
    @NotNull
    Style topStyle = Style.NONE;
    @NotNull
    Color topColor = Color.TRANSPARENT;

    float leftWidth = 0;
    @NotNull
    Style leftStyle = Style.NONE;
    @NotNull
    Color leftColor = Color.TRANSPARENT;

    float rightWidth = 0;
    @NotNull
    Style rightStyle = Style.NONE;
    @NotNull
    Color rightColor = Color.TRANSPARENT;

    float bottomWidth = 0;
    @NotNull
    Style bottomStyle = Style.NONE;
    @NotNull
    Color bottomColor = Color.TRANSPARENT;

    public Border(float width, Style style, Color color) {
        this(width, style, color, width, style, color, width, style, color, width, style, color);
    }

    public Border(float topWidth, Style topStlye, Color topColor, float leftWidth, Style leftStlye, Color leftColor, float rightWidth, Style rightStlye, Color rightColor, float bottomWidth, Style bottomStlye, Color bottomColor) {
        this.topWidth = topWidth;
        this.topStyle = topStlye;
        this.topColor = topColor;
        this.leftWidth = leftWidth;
        this.leftStyle = leftStlye;
        this.leftColor = leftColor;
        this.rightWidth = rightWidth;
        this.rightStyle = rightStlye;
        this.rightColor = rightColor;
        this.bottomWidth = bottomWidth;
        this.bottomStyle = bottomStlye;
        this.bottomColor = bottomColor;
    }

    public Border() {

    }

    public Border empty() {
        return new Border();
    }

    public boolean isEmpty() {
        return (
                (topStyle == Style.NONE || topWidth <= 0 || topColor.a <= 0) &&
                        (leftStyle == Style.NONE || leftWidth <= 0 || leftColor.a <= 0) &&
                        (rightStyle == Style.NONE || rightWidth <= 0 || rightColor.a <= 0) &&
                        (bottomStyle == Style.NONE || bottomWidth <= 0 || bottomColor.a <= 0)
        );
    }

    public void paint(GLGraphics g, float x, float y, float width, float height) {
        //top
        if (topWidth > 0 && topStyle != Style.NONE && topColor.a > 0) {
            drawBorderLine(g, topStyle, topColor, x, y, width, topWidth);
        }
        if (bottomWidth > 0 && bottomStyle != Style.NONE && bottomColor.a > 0) {
            drawBorderLine(g, bottomStyle, bottomColor, x, y + height - bottomWidth, width, bottomWidth);
        }

        if (leftWidth > 0 && leftStyle != Style.NONE && leftColor.a > 0) {
            drawBorderLine(g, leftStyle, leftColor, x, y, leftWidth, height);
        }
        if (rightWidth > 0 && rightStyle != Style.NONE && rightColor.a > 0) {
            drawBorderLine(g, rightStyle, rightColor, x + width - rightWidth, y, rightWidth, height);
        }
    }

    private void drawBorderLine(GLGraphics g, Style style, Color color, float x, float y, float width, float height) {
        if (style == Style.SOLID) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

    public enum Style {
        NONE,
        SOLID
    }
}
