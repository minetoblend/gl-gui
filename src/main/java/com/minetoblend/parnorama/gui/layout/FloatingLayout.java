package com.minetoblend.parnorama.gui.layout;

import com.minetoblend.parnorama.gui.element.Container;
import com.minetoblend.parnorama.gui.element.Element;
import com.minetoblend.parnorama.gui.layout.LayoutManager;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.joml.Vector2f;

public class FloatingLayout implements LayoutManager {

    public static final int TOP = 0x0;
    public static final int BOTTOM = 0x2;
    public static final int RIGHT = 0x1;
    public static final int LEFT = 0x0;
    int direction;


    public FloatingLayout(int direction) {
        if (direction < 0 || direction > 3)
            throw new IllegalArgumentException("Layout Direction " + direction + "must be >= 0 and < 4");
        this.direction = direction;
    }

    public FloatingLayout() {
        this(TOP | LEFT);
    }

    public boolean isTop() {
        return (direction & RIGHT) == 0;
    }

    public boolean isLeft() {
        return (direction & BOTTOM) == 0;
    }

    public void setDirection(int direction) {
        if (direction < 0 || direction > 3)
            throw new IllegalArgumentException("Layout Direction " + direction + "must be >= 0 and < 4");
        this.direction = direction;
    }

    @Override
    public void layoutContainer(Container parent) {
        float curX = isLeft() ? 0 : parent.getWidth();
        float curY = isTop() ? 0 : parent.getHeight();
        for (Element child : parent.getChildren()) {
            Vector2f size = getSize(parent, child);
            child.resize(size.x, size.y);
            System.out.println("layout: " + size.x + " " + size.y);
        }
    }

    private Vector2f getSize(Container parent, Element child) {
        float width = child.getPreferredSize().x;
        float height = child.getPreferredSize().y;

        Vector2f minimum = child.getMinimumSize();
        Vector2f maximum = child.getMaximumSize();
        width = Math.max(width, minimum.x);
        height = Math.max(height, minimum.y);
        width = Math.min(width, maximum.x);
        height = Math.min(height, maximum.y);
        width = Math.min(width, parent.getWidth());
        height = Math.min(height, parent.getHeight());
        return new Vector2f(width, height);
    }

    @Override
    public void addLayoutComponent(Object param, Element element) {

    }

    @Override
    public void removeLayoutComponent(Object param, Element element) {

    }
}
