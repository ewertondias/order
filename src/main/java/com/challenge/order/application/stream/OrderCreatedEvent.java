package com.challenge.order.application.stream;

import java.util.Set;

public record OrderCreatedEvent(String id, Set<OrderProductEvent> products) {
}
