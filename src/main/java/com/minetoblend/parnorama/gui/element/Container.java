package com.minetoblend.parnorama.gui.element;

import com.minetoblend.parnorama.gui.draw.GLGraphics;
import com.minetoblend.parnorama.gui.events.MouseEvent;
import com.minetoblend.parnorama.gui.layout.LayoutManager;

import java.util.ArrayList;
import java.util.List;

public class Container extends Element {

    private List<Element> children = new ArrayList<>();

    private LayoutManager layout;

    public void add(Element e) {
        add(e, null);
    }

    public void add(Element e, Object layoutArgs) {
        if (e.getParent() != null) {
            System.err.println("WARNING: Element already has a parent. Reparenting...");
        }
        e.setParent(this);
        e.setWindow(getWindow());

        children.add(e);
        if (layout != null) {
            layout.addLayoutComponent(layoutArgs, e);
        }
        setDirty(true);
    }

    @Override
    public void paint(GLGraphics g) {
        super.paint(g);
        paintChildren(g);
    }


    @Override
    public void paint() {
        for (Element child : children) {
            if (child.isDirty())
                child.paint();
        }
        super.paint();
    }

    @Override
    public void resize(float width, float height) {
        super.resize(width, height);
        layout();
    }

    private void paintChildren(GLGraphics g) {
        for (Element child : children) {
            child.drawAt(g, child.getX(), child.getY());
        }
    }

    public List<Element> getChildren() {
        return children;
    }

    public void setChildren(List<Element> children) {
        this.children = children;
    }

    public LayoutManager getLayout() {
        return layout;
    }

    public void setLayout(LayoutManager layout) {
        this.layout = layout;
    }

    public void layout() {
        if (this.layout != null) {
            this.layout.layoutContainer(this);
        }
    }


    public Element passMouseEvent(MouseEvent event) {
        if (withinBounds(event.x, event.y)) {
            for (Element child : children) {
                Element e;
                if ((e = child.passMouseEvent(event)) != null) {
                    return e;
                }
            }
            mouseEvent(event.translate(getX(), getY()));
            return this;
        }
        return null;
    }


}
