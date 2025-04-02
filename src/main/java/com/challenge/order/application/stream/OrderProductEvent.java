package com.challenge.order.application.stream;

import java.math.BigDecimal;

public record OrderProductEvent(String id, String name, BigDecimal value) {
}
