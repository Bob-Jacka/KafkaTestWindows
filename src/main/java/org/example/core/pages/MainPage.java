package org.example.core.pages;

import lombok.Getter;
import org.example.core.ElementsFactory;
import org.example.elementClasses.Button;
import org.example.elementClasses.Frame;
import org.example.elementClasses.Menu;
import org.example.elementClasses.TextField;
import org.example.elementClasses.data.ElementEnum;
import org.example.elementClasses.data.LayoutEnum;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.core.GlobalSettings.*;

@Getter
public class MainPage {

    private final Frame mainPage = windowFactory.getElement(ElementEnum.Frame);
    private final Menu menu = (Menu) elementsFactory.getElement(ElementEnum.Menu);
    private final TextField textField = (TextField) elementsFactory.getElement(ElementEnum.TextField);
    private final Button buttonConsumer = (Button) elementsFactory.getElement(ElementEnum.Button);
    private final Button buttonProducer = (Button) elementsFactory.getElement(ElementEnum.Button);
    private final Button stopButton = (Button) elementsFactory.getElement(ElementEnum.Button);
    private final LayoutManager2 layout = layoutFactory.getLayout(LayoutEnum.Border);

    public MainPage() {
        initMainPage();
    }

    private void initMainPage() {
        mainPage.setJMenuBar(menu.getBar());
        mainPage.add(buttonConsumer, BorderLayout.CENTER);
        mainPage.add(buttonProducer, BorderLayout.CENTER);
        mainPage.add(stopButton, BorderLayout.CENTER);
        mainPage.add(textField, BorderLayout.CENTER);
        mainPage.setLayout(layout);
        mainPage.setVisible(true);
        ElementsFactory.setAttributes(buttonProducer, 150, 50, 100, 50, "producer");
        ElementsFactory.setAttributes(buttonConsumer, 150, 50, 100, 150, "consumer");
        ElementsFactory.setAttributes(stopButton, 100, 50, 100, 100, "stop kafka");
        ElementsFactory.setAttributes(textField, 100, 50, 100, 100, "click");
        initListeners();
    }

    private void initListeners() {
        ElementsFactory.setListener(stopButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSignal = true;
            }
        });
        ElementsFactory.setListener(buttonConsumer, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsumerPage();
            }
        });
        ElementsFactory.setListener(buttonProducer, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProducerPage();
            }
        });
    }
}
