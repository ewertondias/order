package com.challenge.order.domain;

import com.challenge.order.application.stream.OrderEvent;

import java.util.Set;

public interface ProcessOrderUseCase {

    void when(Set<OrderEvent> event);

}
