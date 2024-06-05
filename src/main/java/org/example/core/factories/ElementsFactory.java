package org.example.core.factories;

import org.example.elementClasses.Button;
import org.example.elementClasses.Menu;
import org.example.elementClasses.TextArea;
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
            case TextArea:
                return new TextArea();
        }
        return null;
    }
}
