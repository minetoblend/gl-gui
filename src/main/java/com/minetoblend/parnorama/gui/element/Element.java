package com.minetoblend.parnorama.gui.element;

import com.minetoblend.parnorama.gui.Window;
import com.minetoblend.parnorama.gui.draw.GLGraphics;
import com.minetoblend.parnorama.gui.draw.MatrixStack;
import com.minetoblend.parnorama.gui.events.MouseEvent;
import com.minetoblend.parnorama.gui.interfaces.InputListener;
import com.minetoblend.parnorama.gui.style.Style;
import org.joml.Vector2f;

import java.util.HashMap;

public class Element implements InputListener {


    private final MatrixStack matrix = new MatrixStack();
    boolean hover = false;
    boolean active = false;
    private Vector2f preferredSize = new Vector2f();
    private Vector2f minimumSize = new Vector2f();
    private Vector2f maximumSize = new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
    private float width;
    private float height;
    private float x = 1;
    private float y = 1;
    private boolean isDirty = true;
    private Style style = new Style();
    private Window window;
    private Container parent;
    private GLGraphics graphics;
    private boolean mouseWithin = false;
    private Style hoverStyle = new Style();
    private Style activeStyle = new Style();


    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void paint(GLGraphics g) {

        g.clear();
        g.setColor(getStyle().getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        if (getStyle().hasBorder()) {
            getStyle().getBorder().paint(g, 0, 0, getWidth(), getHeight());
        }
    }

    public GLGraphics getGraphics() {
        if (graphics == null) {
            if (isRoot()) {
                graphics = GLGraphics.create(window);
            } else {
                graphics = GLGraphics.create(window, this);
            }
        }
        return graphics;
    }

    public void setGraphics(GLGraphics graphics) {
        this.graphics = graphics;
    }

    public boolean isRoot() {
        return this instanceof Window;
    }

    public void destroy() {

    }

    public Style getStyle() {
        Style style = this.style;

        if (getParent() != null)
            style = style.inheritFrom(getParent().getStyle());

        System.out.println(style);

        if (hover)
            style = hoverStyle.inheritFrom(style);

        if (active)
            style = activeStyle.inheritFrom(style);

        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
        if (parent == null)
            destroy();
    }

    public void resize(float width, float height) {
        this.width = width;
        this.height = height;
        isDirty = true;
        getGraphics().resize((int) width, (int) height);
    }

    public void paint() {
        System.out.println(isVisible());
        if (isVisible()) {

            matrix.reset(0, 0, getWidth(), getHeight());

            GLGraphics g = getGraphics();
            g.enter();
            paint(g);
            g.leave();
            isDirty = false;
        }

    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        this.isDirty = dirty;
        if (parent != null)
            parent.setDirty(true);
    }

    protected void drawAt(GLGraphics g, float x, float y) {
        if (isVisible()) {
            g.bindTexture(getGraphics().getFbo().getTexture());
            g.fillRect(x, y, getWidth(), getHeight());
            g.bindTexture(0);
        }
    }

    public MatrixStack getMatrix() {
        return matrix;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
        setDirty(true);
    }

    public boolean withinBounds(float x, float y) {
        return x >= getX() && x < getX() + getWidth() && y >= getY() && y < getY() + getHeight();
    }

    public Element passMouseEvent(MouseEvent event) {
        if (withinBounds(event.x, event.y)) {
            mouseEvent(event.translate(getX(), getY()));
            return this;
        }
        return null;
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        //System.out.println("Mouse moved over " + this.getClass().getSimpleName());
    }

    public boolean isMouseWithin() {
        return mouseWithin;
    }

    public void setMouseWithin(boolean mouseWithin) {
        this.mouseWithin = mouseWithin;
    }

    @Override
    public void mouseEnter(MouseEvent event) {
        System.out.println("Mouse entered " + this.getClass().getSimpleName());
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
        setDirty(true);
    }

    public Vector2f getPreferredSize() {
        return preferredSize;
    }

    public void setPreferredSize(Vector2f preferredSize) {
        this.preferredSize = preferredSize;
    }

    public Style getHoverStyle() {
        return hoverStyle;
    }

    public void setHoverStyle(Style hoverStyle) {
        this.hoverStyle = hoverStyle;
    }

    public boolean isVisible() {
        return getWidth() > 0 && getHeight() > 0;
    }

    public Vector2f getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(Vector2f maximumSize) {
        this.maximumSize = maximumSize;
    }

    public Vector2f getMinimumSize() {
        return minimumSize;
    }

    public void setMinimumSize(Vector2f minimumSize) {
        this.minimumSize = minimumSize;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.button == 0) {
            active = true;
            setDirty(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (event.button == 0){
            active = false;
            setDirty(true);
        }
    }

    public Style getActiveStyle() {
        return activeStyle;
    }
}
