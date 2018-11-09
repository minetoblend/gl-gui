package com.minetoblend.parnorama.gui.layout;

import com.minetoblend.parnorama.gui.element.Container;
import com.minetoblend.parnorama.gui.element.Element;

public class BorderLayout implements LayoutManager {

    Element top;
    Element bottom;
    Element left;
    Element right;
    Element center;


    @Override
    public void layoutContainer(Container parent) {
        float offsetLeft = 0, offsetTop = 0, offsetBottom = 0, offsetRight = 0;

        if (top != null) {
            float height = Math.min(parent.getHeight(), 100);

            top.move(0, 0);
            top.resize(parent.getWidth(), height);
            offsetTop = height;
        }
        if (bottom != null) {
            float height = Math.min(parent.getHeight() - offsetTop, 100);
            bottom.move(0, parent.getHeight() - height);
            bottom.resize(parent.getWidth(), height);
            offsetBottom = height;
        }
        if (left != null) {
            float width = Math.min(parent.getWidth(), 100);
            left.move(0, offsetTop);
            left.resize(width, Math.max(parent.getHeight() - (offsetTop + offsetBottom), 0));
            offsetLeft = width;
        }
        if (right != null) {
            float width = Math.min(parent.getWidth() - offsetLeft, 100);
            right.move(parent.getWidth()-width, offsetTop);
            right.resize(width, Math.max(parent.getHeight() - (offsetTop + offsetBottom), 0));
            offsetRight = width;
        }
        if (center != null) {
            float width = Math.max(parent.getWidth() - offsetLeft - offsetRight, 0);
            float height = Math.max(parent.getHeight() - offsetTop - offsetBottom, 0);
            center.move(offsetLeft, offsetTop);
            center.resize(width, height);
        }

    }

    @Override
    public void addLayoutComponent(Object param, Element element) {
        if (param instanceof Region) {
            Region region = (Region) param;
            switch (region) {
                case TOP:
                    top = element;
                    break;
                case BOTTOM:
                    bottom = element;
                    break;
                case LEFT:
                    left = element;
                    break;
                case RIGHT:
                    right = element;
                    break;
                case CENTER:
                default:
                    center = element;
                    break;
            }
        }
    }

    @Override
    public void removeLayoutComponent(Object param, Element element) {

    }

    public enum Region {
        CENTER,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }
}
