package org.example.core;

import org.example.elementClasses.Button;
import org.example.elementClasses.Menu;
import org.example.elementClasses.TextField;
import org.example.elementClasses.data.ElementEnum;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ElementsFactory {

    public JComponent getElement(ElementEnum elemName) {
        switch (elemName) {
            case Button:
                return new Button();
            case Label:
                break;
            case TextField:
                return new TextField();
            case Menu:
                return new Menu();
        }
        return null;
    }

    public static void setAttributes(JComponent component, int width, int height, int x, int y, String text) {
        component.setBounds(x, y, width, height);
        component.setToolTipText(text);
    }

    public static void setAttributes(AbstractButton component, int width, int height, int x, int y, String text) {
        component.setBounds(x, y, width, height);
        component.setText(text);
    }

    public static void setListener(Button component, ActionListener event) {
        component.addActionListener(event);
    }

    public static void setListener(TextField component, ActionListener event) {
        component.addActionListener(event);
    }
}
