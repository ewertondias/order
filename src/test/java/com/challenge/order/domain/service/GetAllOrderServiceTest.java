package com.challenge.order.domain.service;

import com.challenge.order.application.api.GetAllOrderProjection;
import com.challenge.order.domain.GetAllOrderUseCase;
import com.challenge.order.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@DisplayName("Get All Orders Service")
@ExtendWith(MockitoExtension.class)
class GetAllOrderServiceTest {

    private GetAllOrderUseCase getAllOrder;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        getAllOrder = new GetAllOrderService(orderRepository);
    }

    @Test
    @DisplayName("Should return orders")
    void shouldReturnOrders() {
        var pageable = PageRequest.of(0, 10);
        var orderProjection = mock(GetAllOrderProjection.class);

        Mockito.when(orderRepository.findAllBy(Mockito.any())).thenReturn(new SliceImpl<>(List.of(orderProjection), pageable, false));

        var orders = getAllOrder.handle(pageable);

        assertNotNull(orders);
        assertEquals(1, orders.getContent().size());
    }

}
