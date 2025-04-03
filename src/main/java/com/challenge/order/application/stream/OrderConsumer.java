package com.challenge.order.application.stream;

import com.challenge.order.domain.ProcessOrderUseCase;
import com.challenge.order.domain.service.OrderValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderConsumer {

    private final OrderValidatorService orderValidator;
    private final ProcessOrderUseCase processOrder;

    public OrderConsumer(OrderValidatorService orderValidator, ProcessOrderUseCase processOrder) {
        this.orderValidator = orderValidator;
        this.processOrder = processOrder;
    }

    @Bean
    public Consumer<OrderEvent> orderIn() {
        return orderEvent -> {
            orderValidator.validate(orderEvent);
            processOrder.when(orderEvent);
        };
    }

}
