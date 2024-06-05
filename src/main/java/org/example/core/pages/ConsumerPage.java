package org.example.core.pages;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.elementClasses.Frame;
import org.example.elementClasses.TextArea;
import org.example.elementClasses.data.Element;
import org.example.elementClasses.data.ElementEnum;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.example.core.GlobalSettings.*;

public class ConsumerPage {

    private final Frame consumerFrame;
    private final TextArea consumerLogs = (TextArea) elementsFactory.getElement(ElementEnum.TextArea);
    private final Properties kafkaProperties = new Properties();

    private ConsumerPage() {
        consumerFrame = Frame.builder()
                .setFrame_FrameName("ConsumerPage")
                .setFrame_Height(700)
                .setFrame_Width(700)
                .setFrame_CloseMode(JFrame.HIDE_ON_CLOSE)
                .build();
        initPage();
    }

    public static ConsumerPage getPage() {
        if (consumerPage == null) {
            consumerPage = new ConsumerPage();
            return consumerPage;
        }
        return consumerPage;
    }

    public void runConsumer() {
        kafkaProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, port);
        kafkaProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "groupId");
        kafkaProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProperties);
//        consumer.subscribe(Collections.singletonList(topic));
        consumer.assign(Collections.singletonList(new TopicPartition(topic, 0)));

        while (!stopSignal) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(3000));
            for (ConsumerRecord<String, String> record : consumerRecords) {
                consumerLogs.append(record.key() + " " + record.value() + "\n");
            }
            if (stopSignal) {
                consumer.close();
            }
            if (!consumerFrame.isActive()) {
                consumerPage = null;
            }
        }
    }

    private void initPage() {
        Element.setAttributes(consumerLogs, consumerFrame.getWidth() - 5, consumerFrame.getHeight() - 5, 0, 0, "click");
        consumerFrame.add(consumerLogs, BorderLayout.CENTER);
        consumerFrame.setVisible(true);
    }
}
