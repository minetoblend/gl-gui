package com.minetoblend.parnorama.gui;

import com.minetoblend.parnorama.gui.draw.GLGraphics;
import com.minetoblend.parnorama.gui.draw.GLState;
import com.minetoblend.parnorama.gui.draw.Renderer;
import com.minetoblend.parnorama.gui.element.Container;
import com.minetoblend.parnorama.gui.element.Element;
import com.minetoblend.parnorama.gui.layout.BorderLayout;
import com.minetoblend.parnorama.gui.style.Style;
import org.lwjgl.opengl.GL;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;

public class Window extends Container {

    public static final Object GLFW_LOCK = new Object();
    private final Thread thread;
    private final WindowEventHandler eventHandler;
    private /*final*/ long window;
    private GLState glState;
    private Renderer renderer;

    boolean hasDrawn = false;

    public Window() {
        if (!GLFWUtils.isInitialized()) GLFWUtils.init();
        thread = new Thread(this::run);
        eventHandler = new WindowEventHandler(this);
        setStyle(Style.getDefault());
        setLayout(new BorderLayout());
    }

    public Window width(int value) {
        this.eventHandler.windowWidth = value;
        return this;
    }

    public Window height(int value) {
        this.eventHandler.windowHeight = value;
        return this;
    }

    public Window size(int width, int height) {
        return width(width).height(height);
    }

    public void run() {
        init();
        while (!glfwWindowShouldClose(window)) {
            if (isDirty()) {
                renderer.draw();
                glfwSwapBuffers(window);

            } else {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            glfwPollEvents();
        }
    }

    private void init() {
        synchronized (GLFW_LOCK) {
            glfwDefaultWindowHints();
            window = glfwCreateWindow(800, 600, "Window", NULL, NULL);
            glfwMakeContextCurrent(window);
        }

        setWindow(this);

        initEventHandlers();
        glfwSwapInterval(1);
        GL.createCapabilities();

        glState = new GLState();
        renderer = new Renderer(this);


    }

    private void initEventHandlers() {
        glfwSetWindowSizeCallback(window, eventHandler::resized);
        glfwSetWindowPosCallback(window, eventHandler::moved);
        glfwSetWindowCloseCallback(window, eventHandler::close);
        glfwSetKeyCallback(window, eventHandler.getInputHandler()::keyEvent);
        glfwSetScrollCallback(window, eventHandler.getInputHandler()::scrollEvent);
        glfwSetMouseButtonCallback(window, eventHandler.getInputHandler()::mouseButtonEvent);
        glfwSetCursorPosCallback(window, eventHandler.getInputHandler()::mouseMoveEvent);
    }

    public Window create() {
        if (!thread.isAlive())
            thread.start();
        else
            System.err.println("Window already created");
        return this;
    }

    @Override
    public float getWidth() {
        return eventHandler.windowWidth;
    }

    @Override
    public float getHeight() {
        return eventHandler.windowHeight;
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }


    public GLState getGLState() {
        return glState;
    }
}
