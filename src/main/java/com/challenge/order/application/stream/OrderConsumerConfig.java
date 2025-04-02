package com.challenge.order.application.stream;

import com.challenge.order.domain.ProcessOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.function.Consumer;

@Configuration
public class OrderConsumerConfig {

    private final ProcessOrderUseCase processOrder;

    public OrderConsumerConfig(ProcessOrderUseCase processOrder) {
        this.processOrder = processOrder;
    }

    @Bean
    public Consumer<Set<OrderEvent>> orderIn() {
        return processOrder::when;
    }

}
