package com.challenge.order.application.stream;

import com.challenge.order.domain.ProcessOrderUseCase;
import com.challenge.order.util.OrderTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Order Consumer")
@ExtendWith(MockitoExtension.class)
class OrderConsumerTest {

    private OrderConsumer orderConsumer;

    @Mock
    private ProcessOrderUseCase processOrder;

    @BeforeEach
    void setUp() {
        orderConsumer = new OrderConsumer(processOrder);
    }

    @Test
    @DisplayName("Should consume order")
    void shouldConsumeOrder() {
        Consumer<OrderEvent> consumer = orderConsumer.orderIn();
        var orderEvent = OrderTestFactory.oneOrderEvent();

        consumer.accept(orderEvent);

        verify(processOrder, times(1)).when(orderEvent);
    }

}
