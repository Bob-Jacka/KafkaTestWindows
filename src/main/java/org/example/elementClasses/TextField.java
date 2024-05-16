package org.example.elementClasses;

import org.example.elementClasses.data.Element;

import javax.swing.*;

public class TextField extends JTextField implements Element {

    private int width = 200;
    private int height = 100;
    private String text;
    private int[] position;

    public TextField() {

    }

    public TextField(int width, int height, String text, int x, int y) {
        this.text = text;
        this.width = width;
        this.height = height;
        this.position = new int[]{x, y};
        initField();
    }

    private void initField() {
        setToolTipText(text);
        setBounds(position[0], position[1], width, height);
    }
}
