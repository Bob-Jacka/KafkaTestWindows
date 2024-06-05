package org.example.elementClasses;

import lombok.Builder;
import org.example.elementClasses.data.Element;

import javax.swing.*;

@Builder(setterPrefix = "setButton_")
public class Button extends JButton implements Element {

    private int width = 100;
    private int height = 50;
    private int positionX;
    private int positionY;
    private String text;

    public Button(int width, int height, int positionX, int positionY, String text) {
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
        this.text = text;
        initButton();
    }

    public Button() {
    }

    private void initButton() {
        setText(text);
        setBounds(positionX, positionY, width, height);
    }
}
