package org.example.core.factories;

import org.example.elementClasses.Frame;
import org.example.elementClasses.data.ElementEnum;

import javax.swing.*;

public class WindowFactory {

    public Frame getElement(ElementEnum elemName) {
        switch (elemName) {
            case MainFrame:
                return new Frame(700, 700, "MainFrame", JFrame.EXIT_ON_CLOSE);
            case ConsumerFrame:
                return new Frame(700, 700, "ConsumerPage", JFrame.HIDE_ON_CLOSE);
            case ProducerFrame:
                return new Frame(700, 700, "ProducerPage", JFrame.HIDE_ON_CLOSE);
        }
        return null;
    }
}
