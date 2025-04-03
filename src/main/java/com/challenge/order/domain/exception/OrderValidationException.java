package com.challenge.order.domain.exception;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.Set;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2011854106231032483L;

    public OrderValidationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations.iterator().next().getMessage());
    }

}
