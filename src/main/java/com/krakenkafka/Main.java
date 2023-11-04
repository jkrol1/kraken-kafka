package krakenkafka;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try {
            final WebsocketClient clientEndPoint = new WebsocketClient(new URI("wss://ws.kraken.com"));

            String payload = "{\"event\":\"subscribe\",\"pair\":[\"XBT/USD\"],\"subscription\":{\"name\":\"ticker\"}}";

            clientEndPoint.addMessageHandler(new WebsocketClient.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            clientEndPoint.sendMessage(payload);
            Thread.sleep(50000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}

class Kraken