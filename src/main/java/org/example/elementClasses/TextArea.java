package org.example.elementClasses;

import lombok.Builder;
import org.example.elementClasses.data.Element;

import javax.swing.*;

@Builder
public class TextArea extends JTextArea implements Element {

    public TextArea() {

    }
}
