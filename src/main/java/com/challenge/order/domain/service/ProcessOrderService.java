package com.challenge.order.domain.service;

import com.challenge.order.application.stream.OrderCreatedEvent;
import com.challenge.order.domain.ProcessOrderUseCase;
import org.springframework.stereotype.Service;

@Service
public class ProcessOrderService implements ProcessOrderUseCase {

    @Override
    public void when(OrderCreatedEvent event) {

    }

}
