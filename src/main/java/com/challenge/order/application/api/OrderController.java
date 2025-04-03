package com.challenge.order.application.api;

import com.challenge.order.application.api.openapi.OrderControllerOpenApi;
import com.challenge.order.domain.GetAllOrderUseCase;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController implements OrderControllerOpenApi {

    private final GetAllOrderUseCase getAllOrder;

    public OrderController(GetAllOrderUseCase getAllOrder) {
        this.getAllOrder = getAllOrder;
    }

    @Override
    @GetMapping
    public Slice<GetAllOrderProjection> getAllOrder(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size);

        return getAllOrder.handle(pageable);
    }

}
