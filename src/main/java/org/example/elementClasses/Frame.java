package org.example.elementClasses;

import lombok.Builder;
import org.example.elementClasses.data.Element;

import javax.swing.*;

@Builder(setterPrefix = "setFrame_")
public class Frame extends JFrame implements Element {

    private int height = 1080;
    private int width = 1920;
    private String frameName;
    private int closeMode;

    public Frame() {
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
