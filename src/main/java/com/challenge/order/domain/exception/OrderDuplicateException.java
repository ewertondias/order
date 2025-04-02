package com.challenge.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OrderDuplicateException extends RuntimeException {

    public OrderDuplicateException() {
        super("Order duplicated");
    }

}
