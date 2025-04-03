package com.challenge.order.application.stream;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record OrderProductEvent(
    @NotEmpty(message = "Product id cannot be null or empty")
    String id,

    @NotEmpty(message = "Product name cannot be null or empty")
    String name,

    BigDecimal value) {
}
