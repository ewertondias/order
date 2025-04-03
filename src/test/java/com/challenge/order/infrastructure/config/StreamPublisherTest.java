package com.challenge.order.infrastructure.config;

import com.challenge.order.util.OrderTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Stream Publisher")
@ExtendWith(MockitoExtension.class)
class StreamPublisherTest {

    private static final String OUTPUT = "orderOut-out-0";

    @Mock
    private StreamBridge streamBridge;

    @InjectMocks
    private StreamPublisher streamPublisher;

    @BeforeEach
    void setUp() {
        streamPublisher = new StreamPublisher(streamBridge);
    }

    @Test
    @DisplayName("Should send event to stream")
    void shouldSendEventToStream() {
        var orderProcessedEvent = OrderTestFactory.oneOrderProcessedEvent();

        streamPublisher.send(orderProcessedEvent);

        verify(streamBridge, times(1)).send(OUTPUT, orderProcessedEvent);
    }

}
