package com.challenge.order.application.stream;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record OrderEvent(
    @NotEmpty(message = "Order id cannot be null or empty")
    String id,

    @NotEmpty(message = "Order date cannot be null or empty")
    String date,

    @Valid
    Set<OrderProductEvent> products) {
}
