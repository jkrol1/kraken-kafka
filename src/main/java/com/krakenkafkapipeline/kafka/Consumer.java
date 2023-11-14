package com.krakenkafkapipeline.kafka;

import com.krakenkafkapipeline.AppConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class.getName());
    private final AppConfig appConfig;

    public Consumer(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    private KafkaConsumer<String, String> createKafkaConsumer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", appConfig.getBootstrapServer());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", appConfig.getConsumerGroupId());
        properties.setProperty("auto.offset.reset", "earliest");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        return new KafkaConsumer<>(properties);
    }

    public void run() {
        try (KafkaConsumer<String, String> consumer = createKafkaConsumer()) {
            addShutdownHook(consumer);
            consumer.subscribe(Collections.singleton(appConfig.getTopicName()));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));
                int recordCount = records.count();
                logger.info("Received " + recordCount + " records");

                for (ConsumerRecord<String, String> record : records) {
                    logger.info(
                            "Consumed record: " + record.value()
                    );
                }
            }
        } catch (WakeupException e) {
            logger.info("Consumer is starting to shut down");
        } catch (Exception e) {
            logger.error("Unexpected exception in the consumer", e);
        } finally {
            logger.info("Consumer is now gracefully shut down");
        }
    }

    private void addShutdownHook(KafkaConsumer<String, String> consumer) {
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Detected a shutdown. Exit in progress...");
            consumer.wakeup();
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
