package org.example.core.factories;

import org.example.elementClasses.data.LayoutEnum;

import java.awt.*;

public class LayoutFactory {

    public LayoutManager2 getLayout(LayoutEnum layout) {
        switch (layout) {
            case Flex:
                return new GridBagLayout();
            case Border:
                return new BorderLayout();
        }
        return null;
    }
}
