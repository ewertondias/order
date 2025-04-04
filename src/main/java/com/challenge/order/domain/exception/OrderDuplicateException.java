package com.challenge.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class OrderDuplicateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5337568855032121217L;

    public OrderDuplicateException() {
        super("Order duplicated");
    }

}
