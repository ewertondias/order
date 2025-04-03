package com.challenge.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OrderException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2011854106231032483L;

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

}
