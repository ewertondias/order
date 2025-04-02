package com.challenge.order.domain.service;

import com.challenge.order.application.stream.OrderEvent;
import com.challenge.order.application.stream.OrderProductEvent;
import com.challenge.order.domain.Order;
import com.challenge.order.domain.OrderProduct;
import com.challenge.order.domain.ProcessOrderUseCase;
import com.challenge.order.domain.enums.OrderStatusEnum;
import com.challenge.order.domain.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProcessOrderService implements ProcessOrderUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessOrderService.class);

    private final OrderRepository orderRepository;

    public ProcessOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void when(Set<OrderEvent> event) {
        event.forEach(orderEvent -> {
            try {
                validateDuplicateOrder(orderEvent);

                var order = createOrder(orderEvent);
                var products = createProduct(orderEvent, order);

                order.addProducts(products);

                orderRepository.save(order);
            } catch (Exception e) {
                log.error("Pedido duplicado");
            }
        });
    }

    private void validateDuplicateOrder(OrderEvent orderEvent) {
        var orderId = UUID.fromString(orderEvent.id());

        if (orderRepository.existsById(orderId)) {
            throw new RuntimeException("Pedido duplicado");
        }
    }

    private Order createOrder(OrderEvent orderEvent) {
        var total = calculateTotalOrderValue(orderEvent.products());
        var orderId = UUID.fromString(orderEvent.id());

        return Order.builder()
            .id(orderId)
            .total(total)
            .status(OrderStatusEnum.PROCESSED)
            .orderDate(LocalDateTime.parse(orderEvent.date()))
            .processingDate(LocalDateTime.now())
            .build();
    }

    private Set<OrderProduct> createProduct(OrderEvent orderEvent, Order order) {
        return orderEvent.products().stream()
            .map(product -> OrderProduct.builder()
                .id(UUID.fromString(product.id()))
                .name(product.name())
                .value(product.value())
                .order(order)
                .build())
            .collect(Collectors.toSet());
    }

    private BigDecimal calculateTotalOrderValue(Set<OrderProductEvent> products) {
        return products.stream()
            .map(OrderProductEvent::value)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
