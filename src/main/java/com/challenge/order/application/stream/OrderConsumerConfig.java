package com.challenge.order.application.stream;

import com.challenge.order.domain.ProcessOrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderConsumerConfig {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumerConfig.class);

    private final ProcessOrderUseCase processOrder;

    public OrderConsumerConfig(ProcessOrderUseCase processOrder) {
        this.processOrder = processOrder;
    }

    @Bean
    public Consumer<OrderCreatedEvent> orderIn() {
        return event -> {
            log.info("Mensagem recebida: {}", event);

            processOrder.when(event);
        };
    }

}
