package org.example.core.pages;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.elementClasses.TextField;
import org.example.elementClasses.data.ElementEnum;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.example.core.GlobalSettings.*;

public class ConsumerPage {

    public static void main(String[] args) {

        final TextField consumerLogs = (TextField) elementsFactory.getElement(ElementEnum.TextField);
        final Properties kafkaProperties = new Properties();

        kafkaProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        kafkaProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "groupId");
        kafkaProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(Collections.singleton(topic));

        while (stopSignal) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(500));
            System.out.println("pulled");
            for (ConsumerRecord<String, String> record : consumerRecords) {
                consumerLogs.setText(record.key() + " " + record.value());
            }
        }
    }
}
