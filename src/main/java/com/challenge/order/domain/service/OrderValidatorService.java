package com.challenge.order.domain.service;

import com.challenge.order.application.stream.OrderEvent;
import com.challenge.order.domain.exception.OrderValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderValidatorService {

    private final Validator validator;

    public OrderValidatorService(Validator validator) {
        this.validator = validator;
    }

    public void validate(OrderEvent orderEvent) {
        Set<ConstraintViolation<OrderEvent>> violations = validator.validate(orderEvent);

        if (!violations.isEmpty()) {
            throw new OrderValidationException(violations);
        }
    }

}
