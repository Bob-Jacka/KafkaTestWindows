package org.example.elementClasses;

import org.example.elementClasses.data.Element;

import javax.swing.*;
import java.util.Arrays;

public class Menu extends JMenu implements Element {

    private JMenuItem[] items;
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

    public JMenuBar getBar() {
        return bar;
    }
}
