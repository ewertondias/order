package com.challenge.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class OrderProduct {

    @Id
    @NotNull
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private String name;

    private BigDecimal value;

    public OrderProduct() {}

    public OrderProduct(OrderProductBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.value = builder.value;
        this.order = builder.order;
    }

    public static OrderProductBuilder builder() {
        return new OrderProductBuilder();
    }

    public UUID getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

}
