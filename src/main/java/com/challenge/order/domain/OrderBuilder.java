package com.challenge.order.domain;

import com.challenge.order.domain.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderBuilder {

    protected UUID id;
    protected BigDecimal total;
    protected OrderStatusEnum status;
    protected LocalDateTime orderDate;
    protected LocalDateTime processingDate;

    public OrderBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public OrderBuilder total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public OrderBuilder status(OrderStatusEnum status) {
        this.status = status;
        return this;
    }

    public OrderBuilder orderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public OrderBuilder processingDate(LocalDateTime processingDate) {
        this.processingDate = processingDate;
        return this;
    }

    public Order build() {
        return new Order(this);
    }

}
