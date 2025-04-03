package com.challenge.order.domain;

import com.challenge.order.domain.enums.OrderStatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Order {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    private BigDecimal total;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatusEnum status;

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private LocalDateTime processingDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<OrderProduct> products = new HashSet<>();

    public Order() {}

    public Order(OrderBuilder builder) {
        this.id = builder.id;
        this.total = builder.total;
        this.status = builder.status;
        this.orderDate = builder.orderDate;
        this.processingDate = builder.processingDate;
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public void addProducts(Set<OrderProduct> products) {
        this.products.addAll(products);
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public LocalDateTime getProcessingDate() {
        return processingDate;
    }

    public Set<OrderProduct> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
