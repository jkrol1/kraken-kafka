package com.krakenkafkapipeline.cli;

import static picocli.CommandLine.Command;

@Command(
        name = "kraken-kafka",
        mixinStandardHelpOptions = true,
        version = "Kraken Kafka Pipeline v0.1.0"
)
public class KrakenKafkaCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Kraken-Kafka producer/consumer app");
    }
}