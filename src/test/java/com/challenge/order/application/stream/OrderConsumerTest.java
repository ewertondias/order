package com.challenge.order.application.stream;

import com.challenge.order.domain.ProcessOrderUseCase;
import com.challenge.order.domain.exception.OrderValidationException;
import com.challenge.order.domain.service.OrderValidatorService;
import com.challenge.order.util.OrderTestFactory;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Order Consumer")
@ExtendWith(MockitoExtension.class)
class OrderConsumerTest {

    private OrderConsumer orderConsumer;

    private ValidatorFactory validatorFactory;

    @Mock
    private ProcessOrderUseCase processOrder;

    @BeforeEach
    void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        OrderValidatorService orderValidator = new OrderValidatorService(validator);
        orderConsumer = new OrderConsumer(orderValidator, processOrder);
    }

    @AfterEach
    void tearDown() {
        validatorFactory.close();
    }

    @Test
    @DisplayName("Should consume order")
    void shouldConsumeOrder() {
        Consumer<OrderEvent> consumer = orderConsumer.orderIn();
        var orderEvent = OrderTestFactory.oneOrderEvent();

        consumer.accept(orderEvent);

        verify(processOrder, times(1)).when(orderEvent);
    }

    @Test
    @DisplayName("Should not consume order if constraint violations")
    void shouldConsumeOrderIfConstraintViolations() {
        Consumer<OrderEvent> consumer = orderConsumer.orderIn();
        var orderEvent = OrderTestFactory.oneOrderEventWithNullFields();

        assertThrows(OrderValidationException.class, () -> consumer.accept(orderEvent));
    }

}
