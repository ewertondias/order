package com.challenge.order.application.stream;

import java.util.Set;

public record OrderEvent(String id, String date, Set<OrderProductEvent> products) {
}
