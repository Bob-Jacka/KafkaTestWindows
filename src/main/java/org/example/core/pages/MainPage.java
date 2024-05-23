package org.example.core.pages;

import lombok.Getter;
import org.example.core.factories.ElementsFactory;
import org.example.elementClasses.Button;
import org.example.elementClasses.Frame;
import org.example.elementClasses.Menu;
import org.example.elementClasses.data.ElementEnum;
import org.example.elementClasses.data.LayoutEnum;

import java.awt.*;

import static org.example.core.GlobalSettings.*;

@Getter
public class MainPage {

    private final Frame mainPage = windowFactory.getElement(ElementEnum.MainFrame);
    private final Menu menu = (Menu) elementsFactory.getElement(ElementEnum.Menu);
    private final Button buttonConsumer = (Button) elementsFactory.getElement(ElementEnum.Button);
    private final Button buttonProducer = (Button) elementsFactory.getElement(ElementEnum.Button);
    private final Button stopButton = (Button) elementsFactory.getElement(ElementEnum.Button);
    private final Button runButton = (Button) elementsFactory.getElement(ElementEnum.Button);
    private final LayoutManager2 layout = layoutFactory.getLayout(LayoutEnum.Border);

    public MainPage() {
        initMainPage();
    }

    private void initMainPage() {
        mainPage.setJMenuBar(menu.getBar());
        mainPage.add(buttonConsumer, BorderLayout.WEST);
        mainPage.add(buttonProducer, BorderLayout.WEST);
        mainPage.add(stopButton, BorderLayout.WEST);
        mainPage.add(runButton, BorderLayout.WEST);
        mainPage.setLayout(layout);
        mainPage.setVisible(true);
        ElementsFactory.setAttributes(buttonProducer, 150, 50, 100, 50, "producer");
        ElementsFactory.setAttributes(buttonConsumer, 150, 50, 100, 100, "consumer");
        ElementsFactory.setAttributes(stopButton, 150, 50, 100, 150, "stop kafka");
        ElementsFactory.setAttributes(runButton, 150, 50, 100, 200, "run kafka");
        initListeners();
    }

    private void initListeners() {
        ElementsFactory.setListener(stopButton, e -> {
            stopSignal = true;
            System.out.println("Button has been stopped");
        });
        ElementsFactory.setListener(buttonConsumer, e -> {
            Thread consumerThread = new Thread(() -> new ConsumerPage().runConsumer());
            consumerThread.start();
        });
        ElementsFactory.setListener(buttonProducer, e -> {
            Thread producerThread = new Thread(() -> new ProducerPage().runProducer());
            producerThread.start();
        });
        ElementsFactory.setListener(runButton, e -> {
            stopSignal = false;
            System.out.println("Button has been ran");
        });
    }
}
