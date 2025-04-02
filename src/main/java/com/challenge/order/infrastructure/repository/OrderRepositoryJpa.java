package com.challenge.order.infrastructure.repository;

import com.challenge.order.domain.Order;
import com.challenge.order.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepositoryJpa extends OrderRepository, JpaRepository<Order, UUID> {
}
