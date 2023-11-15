package com.krakenkafkapipeline.cli;

import com.krakenkafkapipeline.AppConfig;
import com.krakenkafkapipeline.kafka.ProducerFactory;
import com.krakenkafkapipeline.kraken.KrakenWSSPayload;
import com.krakenkafkapipeline.kraken.TickerMessageToKafkaHandler;
import com.krakenkafkapipeline.kraken.WebsocketClient;
import org.apache.kafka.clients.producer.KafkaProducer;

import static picocli.CommandLine.Command;

import java.net.URI;
import java.net.URISyntaxException;

@Command(name = "produce")
public class ProduceSubCommand extends BaseSubCommand {
    public ProduceSubCommand(AppConfig appConfig) {
        super(appConfig);
    }

    @Override
    public void run() {
        try {
            WebsocketClient clientEndPoint = new WebsocketClient(new URI("wss://ws.kraken.com"));
            KafkaProducer<String, String> kafkaProducer = ProducerFactory.create(appConfig);
            clientEndPoint.addMessageHandler(new TickerMessageToKafkaHandler(kafkaProducer, appConfig.getTopicName()));
            clientEndPoint.sendMessage(KrakenWSSPayload.Ticker);
            while (true) ;


        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
