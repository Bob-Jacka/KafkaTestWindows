package org.example.core.pages;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.core.factories.ElementsFactory;
import org.example.elementClasses.Frame;
import org.example.elementClasses.TextArea;
import org.example.elementClasses.data.ElementEnum;

import java.awt.*;
import java.util.Properties;
import java.util.Random;

import static org.example.core.GlobalSettings.*;

public class ProducerPage {

    private final Frame producerPage = windowFactory.getElement(ElementEnum.ProducerFrame);
    private final TextArea producerLogs = (TextArea) elementsFactory.getElement(ElementEnum.TextArea);
    private final Properties kafkaProperties = new Properties();
    private final Random random = new Random();

    public ProducerPage() {
        initPage();
    }

    public void runProducer() {
        kafkaProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        kafkaProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProperties);

        while (!stopSignal) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key", String.valueOf(random.nextInt(1, 100)));
            producerLogs.append(record.key() + " " + record.value() + "\n");
            producer.send(record);
            if (stopSignal) {
                producer.flush();
                producer.close();
            }
        }
    }

    private void initPage() {
        ElementsFactory.setAttributes(producerLogs, producerPage.getWidth() - 5, producerPage.getHeight() - 5, 0, 0, "click");
        producerPage.add(producerLogs, BorderLayout.CENTER);
        producerPage.setVisible(true);
    }
}