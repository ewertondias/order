package com.challenge.order.application.stream;

import com.challenge.order.domain.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.Set;

public record OrderProcessedEvent(String id, BigDecimal total, OrderStatusEnum status, String orderDate,
                                  String processingDate, Set<OrderProductEvent> products) {
}
