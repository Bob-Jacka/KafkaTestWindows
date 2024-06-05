package org.example.elementClasses;

import lombok.Getter;
import org.example.elementClasses.data.Element;

import javax.swing.*;
import java.util.Arrays;

public class Menu extends JMenu implements Element {

    private JMenuItem[] items;
    @Getter
    private JMenuBar bar;

    public Menu() {
        initMenu();
    }

    private void initMenu() {
        setText("Options");
        items = new JMenuItem[]{
                new JMenuItem("First item"),
                new JMenuItem("First item")
        };
        Arrays.stream(items).forEach(this::add);
        bar = new JMenuBar();
        bar.add(this);
    }
}
