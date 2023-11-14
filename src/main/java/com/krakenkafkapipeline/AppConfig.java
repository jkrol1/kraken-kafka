package com.krakenkafkapipeline;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private final Properties properties;

    public AppConfig() {
        properties = new Properties();
        loadConfiguration();
    }

    private void loadConfiguration() {
        try (InputStream inputStream = getClass().getResourceAsStream("/config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("config.properties not found in resources");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBootstrapServer() {
        return properties.getProperty("bootstrap.servers");
    }

    public String getTopicName() {
        return properties.getProperty("topic.name");
    }

    public String getConsumerGroupId() {
        return properties.getProperty("consumer.group.id");
    }

}