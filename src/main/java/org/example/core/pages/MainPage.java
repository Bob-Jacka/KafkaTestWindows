package org.example.core.pages;

import org.example.elementClasses.Button;
import org.example.elementClasses.Frame;
import org.example.elementClasses.Menu;
import org.example.elementClasses.data.Element;
import org.example.elementClasses.data.ElementEnum;
import org.example.elementClasses.data.LayoutEnum;

import javax.swing.*;
import java.awt.*;

import static org.example.core.GlobalSettings.*;

public class MainPage {

    private final Frame mainFrame;
    private final Menu menu = (Menu) elementsFactory.getElement(ElementEnum.Menu);
    private Button buttonCreateConsumer;
    private Button buttonCreateProducer;
    private Button stopButton;
    private Button runButton;
    private final LayoutManager2 layout = layoutFactory.getLayout(LayoutEnum.Border);

    private MainPage() {
        mainFrame = Frame.builder()
                .setFrame_FrameName("MainFrame")
                .setFrame_Height(800)
                .setFrame_Width(800)
                .setFrame_CloseMode(JFrame.EXIT_ON_CLOSE)
                .build();
        initMainPage();
    }

    public static MainPage getPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
            return mainPage;
        }
        return mainPage;
    }

    private void initMainPage() {
        mainFrame.setJMenuBar(menu.getBar());
        buttonCreateConsumer = Button.builder()
                .setButton_Width(mainFrame.getWidth())
                .setButton_Height(50)
                .setButton_PositionX(100)
                .setButton_PositionY(100)
                .setButton_Text("Consumer")
                .build();
        buttonCreateProducer = Button.builder()
                .setButton_Width(mainFrame.getWidth())
                .setButton_Height(50)
                .setButton_PositionX(100)
                .setButton_PositionY(50)
                .setButton_Text("Producer")
                .build();
        stopButton = Button.builder()
                .setButton_Width(mainFrame.getWidth())
                .setButton_Height(50)
                .setButton_PositionX(100)
                .setButton_PositionY(150)
                .setButton_Text("Stop kafka")
                .build();
        runButton = Button.builder()
                .setButton_Width(mainFrame.getWidth())
                .setButton_Height(50)
                .setButton_PositionX(100)
                .setButton_PositionY(200)
                .setButton_Text("Run kafka")
                .build();
        Element.addElements(BorderLayout.CENTER, mainFrame, buttonCreateConsumer,
                buttonCreateProducer, stopButton, runButton);
        mainFrame.setLayout(layout);
        initListeners();
    }

    private void initListeners() {
        Element.setListener(stopButton, e -> {
            stopSignal = true;
            System.out.println("Button has been stopped");
        });
        Element.setListener(buttonCreateConsumer, e -> {
            Thread consumerThread = new Thread(() -> ConsumerPage.getPage().runConsumer());
            consumerThread.start();
            System.out.println(consumerThread.getName());
        });
        Element.setListener(buttonCreateProducer, e -> {
            Thread producerThread = new Thread(() -> ProducerPage.getPage().runProducer());
            producerThread.start();
            System.out.println(producerThread.getName());
        });
        Element.setListener(runButton, e -> {
            if (consumerPage != null) {
                stopSignal = false;
                consumerPage.runConsumer();
                System.out.println("Button has been ran");
            }
        });
    }
}
