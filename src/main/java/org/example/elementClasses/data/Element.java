package org.example.elementClasses.data;

import org.example.elementClasses.Frame;
import org.example.elementClasses.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public interface Element {

    static void addElements(Object constrain, Frame whereToAdd, Component... components) {
        for (Component comp : components) {
            whereToAdd.add(comp, constrain);
        }
    }

    static void setAttributes(JComponent component, int width, int height, int x, int y, String text) {
        component.setBounds(x, y, width, height);
        component.setToolTipText(text);
    }

    static void setListener(org.example.elementClasses.Button component, ActionListener event) {
        component.addActionListener(event);
    }

    static void setListener(TextField component, ActionListener event) {
        component.addActionListener(event);
    }
}
