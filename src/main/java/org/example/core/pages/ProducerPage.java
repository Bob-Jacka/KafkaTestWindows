package org.example.core.pages;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.elementClasses.Frame;
import org.example.elementClasses.TextArea;
import org.example.elementClasses.data.Element;
import org.example.elementClasses.data.ElementEnum;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import java.util.Random;

import static org.example.core.GlobalSettings.*;

public class ProducerPage {

    private final Frame producerFrame;
    private final TextArea producerLogs = (TextArea) elementsFactory.getElement(ElementEnum.TextArea);
    private final Properties kafkaProperties = new Properties();
    private final Random random = new Random();

    private ProducerPage() {
        producerFrame = Frame.builder()
                .setFrame_FrameName("ProducerPage")
                .setFrame_Height(700)
                .setFrame_Width(700)
                .setFrame_CloseMode(JFrame.HIDE_ON_CLOSE)
                .build();
        initPage();
    }

    public static ProducerPage getPage() {
        if (producerPage == null) {
            producerPage = new ProducerPage();
            return producerPage;
        }
        return producerPage;
    }

    public void runProducer() {
        kafkaProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, port);
        kafkaProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProperties);

//        while (!stopSignal) {
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, 0, "key", String.valueOf(random.nextInt(1, 100)));
            producerLogs.append(record.key() + " " + record.value() + "\n");
            producer.send(record, (metadata, e) -> {
                if (e == null) {
                    System.out.println(
                            "Metadate: " + metadata.partition()
                                    + "Topic: " + metadata.topic()
                    );
                } else {
                    System.out.println(e.getStackTrace());
                }
            });
            if (stopSignal) {
                producer.flush();
                producer.close();
            }
            if (!producerFrame.isActive()) {
                producerPage = null;
            }
        }
    }

    private void initPage() {
        Element.setAttributes(producerLogs, producerFrame.getWidth() - 5, producerFrame.getHeight() - 5, 0, 0, "click");
        producerFrame.add(producerLogs, BorderLayout.CENTER);
        producerFrame.setVisible(true);
    }
}