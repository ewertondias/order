package com.challenge.order.domain;

import com.challenge.order.application.stream.OrderEvent;
import com.challenge.order.application.stream.OrderProcessedEvent;

public interface ProcessOrderUseCase {

    void when(OrderEvent orderEvent);
    void on(OrderProcessedEvent orderProcessedEvent);

}
