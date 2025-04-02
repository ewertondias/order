package com.challenge.order.domain.repository;

import com.challenge.order.domain.Order;

import java.util.UUID;

public interface OrderRepository {

    Order save(Order order);

    boolean existsById(UUID id);

}
