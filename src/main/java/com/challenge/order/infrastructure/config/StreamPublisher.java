package com.challenge.order.infrastructure.config;

import com.challenge.order.application.stream.OrderProcessedEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StreamPublisher {

    private static final String OUTPUT = "orderOut-out-0";

    private final StreamBridge streamBridge;

    public StreamPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void send(OrderProcessedEvent event) {
        streamBridge.send(OUTPUT, event);
    }

}
