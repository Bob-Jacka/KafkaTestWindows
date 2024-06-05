package org.example.core;

import lombok.experimental.UtilityClass;
import org.example.core.factories.ElementsFactory;
import org.example.core.factories.LayoutFactory;
import org.example.core.pages.ConsumerPage;
import org.example.core.pages.MainPage;
import org.example.core.pages.ProducerPage;

@UtilityClass
public class GlobalSettings {

    public ElementsFactory elementsFactory = new ElementsFactory();
    public LayoutFactory layoutFactory = new LayoutFactory();
    public final String topic = "testTopic";
    public boolean stopSignal = false;
    public final String port = "127.0.0.1:9092";
    public MainPage mainPage;
    public ConsumerPage consumerPage;
    public ProducerPage producerPage;
}
