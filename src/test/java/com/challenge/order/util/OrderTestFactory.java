package com.challenge.order.util;

import com.challenge.order.application.stream.OrderEvent;
import com.challenge.order.application.stream.OrderProcessedEvent;
import com.challenge.order.application.stream.OrderProductEvent;
import com.challenge.order.domain.Order;
import com.challenge.order.domain.OrderProduct;
import com.challenge.order.domain.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class OrderTestFactory {

    public static Order oneOrder() {
        var order = Order.builder()
            .id(UUID.randomUUID())
            .total(BigDecimal.TEN)
            .status(OrderStatusEnum.PROCESSED)
            .orderDate(LocalDateTime.now())
            .processingDate(LocalDateTime.now())
            .build();

        order.addProducts(Set.of(oneOrderProduct()));

        return order;
    }

    public static OrderProduct oneOrderProduct() {
        return OrderProduct.builder()
            .id(UUID.randomUUID())
            .name("Test Product")
            .value(BigDecimal.TEN)
            .build();
    }

    public static OrderEvent oneOrderEvent() {
        return new OrderEvent(
            UUID.randomUUID().toString(),
            LocalDateTime.now().toString(),
            Set.of(oneOrderProductEvent())
        );
    }

    public static OrderEvent oneOrderEventWithNullFields() {
        return new OrderEvent(
            null,
            LocalDateTime.now().toString(),
            Set.of(oneOrderProductEvent())
        );
    }

    public static OrderProductEvent oneOrderProductEvent() {
        return new OrderProductEvent(
            UUID.randomUUID().toString(),
            "Test Product 1",
            BigDecimal.TEN
        );
    }

    public static OrderProcessedEvent oneOrderProcessedEvent() {
        return new OrderProcessedEvent(
            UUID.randomUUID().toString(),
            BigDecimal.TEN,
            OrderStatusEnum.PROCESSED,
            LocalDateTime.now(),
            LocalDateTime.now(),
            Set.of(oneOrderProductEvent())
        );
    }

}
