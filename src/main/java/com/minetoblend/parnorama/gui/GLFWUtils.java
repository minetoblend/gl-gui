package com.minetoblend.parnorama.gui;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;

public class GLFWUtils {

    private static boolean initialized;

    public synchronized static void init(){
        if(!initialized){
            initialized = true;
            GLFWErrorCallback.createPrint(System.err).set();

            if ( !glfwInit() )
                throw new IllegalStateException("Unable to initialize GLFW");
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
