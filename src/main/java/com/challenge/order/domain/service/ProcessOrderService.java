package com.challenge.order.domain.service;

import com.challenge.order.application.stream.OrderEvent;
import com.challenge.order.application.stream.OrderProcessedEvent;
import com.challenge.order.application.stream.OrderProductEvent;
import com.challenge.order.domain.ProcessOrderUseCase;
import com.challenge.order.domain.assembler.OrderAssembler;
import com.challenge.order.domain.repository.OrderRepository;
import com.challenge.order.infrastructure.config.StreamPublisher;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Service
public class ProcessOrderService implements ProcessOrderUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessOrderService.class);

    private final OrderRepository orderRepository;
    private final StreamPublisher streamPublisher;

    public ProcessOrderService(OrderRepository orderRepository, StreamPublisher streamPublisher) {
        this.orderRepository = orderRepository;
        this.streamPublisher = streamPublisher;
    }

    @Override
    @Transactional
    public void when(OrderEvent orderEvent) {
        try {
            this.validateDuplicateOrder(orderEvent);

            var total = calculateTotalOrderValue(orderEvent.products());
            var order = OrderAssembler.toOrder(orderEvent, total);
            var products = OrderAssembler.toOrderProduct(orderEvent, order);

            order.addProducts(products);

            var orderProcessed = orderRepository.save(order);

            var orderProcessedEvent = OrderAssembler.toOrderProcessedEvent(orderProcessed);

            this.on(orderProcessedEvent);
        } catch (Exception e) {
            log.error("Pedido duplicado");
        }
    }

    @Override
    public void on(OrderProcessedEvent orderProcessedEvent) {
        streamPublisher.send(orderProcessedEvent);
    }

    private void validateDuplicateOrder(OrderEvent orderEvent) {
        var orderId = UUID.fromString(orderEvent.id());

        if (orderRepository.existsById(orderId)) {
            throw new RuntimeException("Pedido duplicado");
        }
    }

    private BigDecimal calculateTotalOrderValue(Set<OrderProductEvent> products) {
        return products.stream()
            .map(OrderProductEvent::value)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
