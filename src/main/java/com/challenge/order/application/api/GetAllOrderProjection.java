package com.challenge.order.application.api;

import com.challenge.order.application.stream.OrderProductEvent;
import com.challenge.order.domain.OrderProduct;
import com.challenge.order.domain.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public interface GetAllOrderProjection {

    UUID getId();
    BigDecimal getTotal();
    OrderStatusEnum getStatus();
    LocalDateTime getOrderDate();
    LocalDateTime getProcessingDate();

    @JsonIgnore
    Set<OrderProduct> getProducts();

    @JsonProperty("products")
    default Set<OrderProductEvent> getOrderProducts() {
        return this.getProducts().stream()
            .map(product -> new OrderProductEvent(
                product.getId().toString(),
                product.getName(),
                product.getValue()
            ))
            .collect(Collectors.toSet());
    }

}
