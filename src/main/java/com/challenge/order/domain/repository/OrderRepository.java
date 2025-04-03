package com.challenge.order.domain.repository;

import com.challenge.order.application.api.GetAllOrderProjection;
import com.challenge.order.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface OrderRepository {

    Order save(Order order);
    boolean existsById(UUID id);
    Slice<GetAllOrderProjection> findAllBy(Pageable pageable);

}
