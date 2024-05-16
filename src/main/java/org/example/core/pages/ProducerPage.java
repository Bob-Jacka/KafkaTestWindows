package org.example.core.pages;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

import static org.example.core.GlobalSettings.stopSignal;
import static org.example.core.GlobalSettings.topic;

public class ProducerPage {

    public static void main(String[] args) {

        final Properties kafkaProperties = new Properties();
        final Random random = new Random();

        kafkaProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        kafkaProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProperties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, String.valueOf(random.nextInt()));

        while (stopSignal) {
            producer.send(record);
        }
        producer.flush();
        producer.close();
    }
}