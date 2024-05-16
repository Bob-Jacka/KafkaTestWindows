package org.example.core;

import org.example.elementClasses.Frame;
import org.example.elementClasses.data.ElementEnum;

import javax.swing.*;

public class WindowFactory {

    public Frame getElement(ElementEnum elemName) {
        switch (elemName) {
            case Frame:
                return new Frame(700, 700, "MainFrame", JFrame.EXIT_ON_CLOSE);
        }
        return null;
    }
}
