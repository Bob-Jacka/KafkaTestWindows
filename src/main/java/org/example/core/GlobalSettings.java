package org.example.core;

public abstract class GlobalSettings {

    public static WindowFactory windowFactory = new WindowFactory();
    public static ElementsFactory elementsFactory = new ElementsFactory();
    public static LayoutFactory layoutFactory = new LayoutFactory();
    public static final String topic = "testTopic";
    public static boolean stopSignal = false;
}
