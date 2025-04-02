package com.challenge.order.domain.assembler;

import com.challenge.order.application.stream.OrderEvent;
import com.challenge.order.application.stream.OrderProcessedEvent;
import com.challenge.order.application.stream.OrderProductEvent;
import com.challenge.order.domain.Order;
import com.challenge.order.domain.OrderProduct;
import com.challenge.order.domain.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderAssembler {

    private OrderAssembler() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static Order toOrder(OrderEvent orderEvent, BigDecimal total) {
        var orderId = UUID.fromString(orderEvent.id());

        return Order.builder()
            .id(orderId)
            .total(total)
            .status(OrderStatusEnum.PROCESSED)
            .orderDate(LocalDateTime.parse(orderEvent.date()))
            .processingDate(LocalDateTime.now())
            .build();
    }

    public static Set<OrderProduct> toOrderProduct(OrderEvent orderEvent, Order order) {
        return orderEvent.products().stream()
            .map(product -> OrderProduct.builder()
                .id(UUID.fromString(product.id()))
                .name(product.name())
                .value(product.value())
                .order(order)
                .build())
            .collect(Collectors.toSet());
    }

    public static OrderProcessedEvent toOrderProcessedEvent(Order order) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        var orderProductEvents = toOrderProductEvent(order);

        return new OrderProcessedEvent(
            order.getId().toString(),
            order.getTotal(),
            order.getStatus(),
            order.getOrderDate().format(dateTimeFormatter),
            order.getProcessingDate().format(dateTimeFormatter),
            orderProductEvents
        );
    }

    public static Set<OrderProductEvent> toOrderProductEvent(Order order) {
        return order.getProducts().stream()
            .map(orderProduct -> new OrderProductEvent(
                orderProduct.getId().toString(),
                orderProduct.getName(),
                orderProduct.getValue()
            ))
            .collect(Collectors.toSet());
    }

}
