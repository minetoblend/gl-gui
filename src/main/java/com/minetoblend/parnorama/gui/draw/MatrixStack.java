package com.minetoblend.parnorama.gui.draw;

import com.sun.istack.internal.NotNull;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;

import java.util.Stack;

public class MatrixStack {

    @NotNull Matrix4f currentMatrix = new Matrix4f();

    private final Stack<Matrix4f> stack = new Stack<>();

    public void reset(){
        stack.clear();
        currentMatrix.identity();
    }

    public void reset(float x, float y, float w, float h){
        stack.clear();
        currentMatrix.identity().ortho(x,x+w,y+h,y,-100,100);
    }

    public void translate(float x, float y, float z){
        currentMatrix.translate(x,y,z);
    }

    public void rotate(float angle, float x, float y, float z){
        currentMatrix.rotate(angle, x, y, z);
    }

    public void scale(float x, float y, float z){
        currentMatrix.scale(x,y,z);
    }

    public void push(){
        stack.push(currentMatrix);
        currentMatrix = new Matrix4f(currentMatrix);
    }

    public Matrix4f pop(){
        Matrix4f m = currentMatrix;
        currentMatrix = stack.pop();
        return m;
    }

    void loadMatrix(){
        float[] v = new float[16];
        currentMatrix.get(v);
        glLoadMatrixf(v);
    }
}
