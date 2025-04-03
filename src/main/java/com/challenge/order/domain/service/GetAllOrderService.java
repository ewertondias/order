package com.challenge.order.domain.service;

import com.challenge.order.application.api.GetAllOrderProjection;
import com.challenge.order.domain.GetAllOrderUseCase;
import com.challenge.order.domain.repository.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class GetAllOrderService implements GetAllOrderUseCase {

    private final OrderRepository orderRepository;

    public GetAllOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Slice<GetAllOrderProjection> handle(Pageable pageable) {
        return orderRepository.findAllBy(pageable);
    }

}
