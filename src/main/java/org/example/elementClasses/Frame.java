package org.example.elementClasses;

import org.example.elementClasses.data.Element;

import javax.swing.*;

public class Frame extends JFrame implements Element {

    private int height = 1080;
    private int width = 1920;
    private int closeMode;
    private String frameName;

    private Frame() {
    }

    public Frame(int width, int height, String frameName, int closeMode) {
        this.closeMode = closeMode;
        this.width = width;
        this.height = height;
        this.frameName = frameName;
        initFrame();
    }

    private void initFrame() {
        setTitle(frameName);
        setSize(width, height);
        setDefaultCloseOperation(closeMode);
        setLayout(null);
        setVisible(true);
    }
}
