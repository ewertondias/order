package com.challenge.order.application.api.openapi;

import com.challenge.order.application.api.GetAllOrderProjection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Order", description = "Order Management")
public interface OrderControllerOpenApi {

    @Operation(summary = "Returns a list of orders", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Orders returned successfully"),
        @ApiResponse(responseCode = "400", description = "Request to return orders invalid")
    })
    Slice<GetAllOrderProjection> getAllOrder(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size);

}
