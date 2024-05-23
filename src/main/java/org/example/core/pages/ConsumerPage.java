package org.example.core.pages;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.core.factories.ElementsFactory;
import org.example.elementClasses.Frame;
import org.example.elementClasses.TextArea;
import org.example.elementClasses.data.ElementEnum;

import java.awt.*;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.example.core.GlobalSettings.*;

public class ConsumerPage {

    private final Frame consumerPage = windowFactory.getElement(ElementEnum.ConsumerFrame);
    private final TextArea consumerLogs = (TextArea) elementsFactory.getElement(ElementEnum.TextArea);
    private final Properties kafkaProperties = new Properties();

    public ConsumerPage() {
        initPage();
    }

    public void runConsumer() {
        kafkaProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        kafkaProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "groupId");
        kafkaProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(Collections.singleton(topic));

        while (!stopSignal) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(3000));
            int counter = 0;
            for (ConsumerRecord<String, String> record : consumerRecords.records(topic)) {
                consumerLogs.insert(record.key() + " " + record.value() + "\n", counter);
                consumerLogs.repaint();
            }
            if (stopSignal) {
                consumer.close();
            }
        }
    }

    private void initPage() {
        ElementsFactory.setAttributes(consumerLogs, consumerPage.getWidth() - 5, consumerPage.getHeight() - 5, 0, 0, "click");
        consumerPage.add(consumerLogs, BorderLayout.CENTER);
        consumerPage.setVisible(true);
    }
}
