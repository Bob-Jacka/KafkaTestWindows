package org.example.elementClasses;

import org.example.elementClasses.data.Element;

import javax.swing.*;

public class Button extends JButton implements Element {

    private int width = 100;
    private int height = 50;
    private String text;
    private int[] position;

    public Button() {

    }

    public Button(int width, int height, String text, int x, int y) {
        this.text = text;
        this.width = width;
        this.height = height;
        this.position = new int[]{x, y};
        initButton();
    }

    private void initButton() {
        setText(text);
        setBounds(position[0], position[1], width, height);
    }
}
