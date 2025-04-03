package com.challenge.order.domain.service;

import com.challenge.order.application.stream.OrderProcessedEvent;
import com.challenge.order.domain.ProcessOrderUseCase;
import com.challenge.order.domain.exception.OrderDuplicateException;
import com.challenge.order.domain.repository.OrderRepository;
import com.challenge.order.infrastructure.config.StreamPublisher;
import com.challenge.order.util.OrderTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Process Order Service")
@ExtendWith(MockitoExtension.class)
class ProcessOrderServiceTest {

    private ProcessOrderUseCase processOrder;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private StreamPublisher streamPublisher;

    @BeforeEach
    void setUp() {
        processOrder = new ProcessOrderService(orderRepository, streamPublisher);
    }

    @Test
    @DisplayName("Should process order")
    void shouldProcessOrder() {
        var orderEvent = OrderTestFactory.oneOrderEvent();

        when(orderRepository.existsById(any())).thenReturn(false);
        when(orderRepository.save(any())).thenReturn(OrderTestFactory.oneOrder());

        processOrder.when(orderEvent);

        verify(streamPublisher, times(1)).send(any(OrderProcessedEvent.class));
    }

    @Test
    @DisplayName("Should not process order if it does save")
    void shouldNotProcessOrder() {
        var orderEvent = OrderTestFactory.oneOrderEvent();

        when(orderRepository.existsById(any())).thenReturn(false);
        when(orderRepository.save(any())).thenReturn(null);

        processOrder.when(orderEvent);

        verify(streamPublisher, never()).send(any(OrderProcessedEvent.class));
    }

    @Test
    @DisplayName("Should not process duplicate order")
    void shouldNotProcessDuplicateOrder() {
        var orderEvent = OrderTestFactory.oneOrderEvent();

        when(orderRepository.existsById(any())).thenReturn(true);

        assertThrows(OrderDuplicateException.class, () -> processOrder.when(orderEvent));

        verify(streamPublisher, never()).send(any(OrderProcessedEvent.class));
    }

}
