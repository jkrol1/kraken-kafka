package com.krakenkafkapipeline.kraken;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TickerMessageToKafkaHandler implements WebsocketClient.MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(TickerMessageToKafkaHandler.class.getName());

    private final KafkaProducer<String, String> kafkaProducer;
    private final String topic;

    public TickerMessageToKafkaHandler(KafkaProducer<String, String> kafkaProducer, String topic) {
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
    }

    @Override
    public void handleMessage(String message) {
        try {
            JSONArray response = new JSONArray(message);
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
            kafkaProducer.send(record);
            logger.info("Message sent: " + message);
        } catch (JSONException ignored) {
        }
    }
}
