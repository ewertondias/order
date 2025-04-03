package com.challenge.order.application.stream;

import com.challenge.order.domain.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record OrderProcessedEvent(String id, BigDecimal total, OrderStatusEnum status, LocalDateTime orderDate,
                                  LocalDateTime processingDate, Set<OrderProductEvent> products) {
}
