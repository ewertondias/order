package com.challenge.order.domain.service;

import com.challenge.order.application.stream.OrderEvent;
import com.challenge.order.application.stream.OrderProcessedEvent;
import com.challenge.order.application.stream.OrderProductEvent;
import com.challenge.order.domain.ProcessOrderUseCase;
import com.challenge.order.domain.assembler.OrderAssembler;
import com.challenge.order.domain.exception.OrderDuplicateException;
import com.challenge.order.domain.exception.OrderException;
import com.challenge.order.domain.repository.OrderRepository;
import com.challenge.order.infrastructure.config.StreamPublisher;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.nonNull;

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

            if (nonNull(orderProcessed)) {
                var orderProcessedEvent = OrderAssembler.toOrderProcessedEvent(orderProcessed);

                this.on(orderProcessedEvent);
            }
        } catch (OrderDuplicateException ex) {
            log.error("Error consuming order id {} - {}", orderEvent.id(), ex.getMessage());

            throw new OrderDuplicateException();
        } catch (Exception ex) {
            throw new OrderException(ex.getMessage(), ex);
        }
    }

    @Override
    public void on(OrderProcessedEvent orderProcessedEvent) {
        streamPublisher.send(orderProcessedEvent);
    }

    private void validateDuplicateOrder(OrderEvent orderEvent) {
        var orderId = UUID.fromString(orderEvent.id());

        if (orderRepository.existsById(orderId)) {
            throw new OrderDuplicateException();
        }
    }

    private BigDecimal calculateTotalOrderValue(Set<OrderProductEvent> products) {
        if (ObjectUtils.isEmpty(products)) {
            return BigDecimal.ZERO;
        }

        return products.stream()
            .map(OrderProductEvent::value)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
