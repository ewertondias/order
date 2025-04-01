package com.challenge.order.domain;

import com.challenge.order.application.stream.OrderCreatedEvent;

public interface ProcessOrderUseCase {

    void when(OrderCreatedEvent event);

}
