package com.challenge.order.domain;

import com.challenge.order.application.api.GetAllOrderProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GetAllOrderUseCase {

    Slice<GetAllOrderProjection> handle(Pageable pageable);

}
