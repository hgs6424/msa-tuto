package com.baki.order.application.port.in;

import com.baki.order.domain.OrderDto;

public interface GetOrderUseCase {
    OrderDto get(Long orderId);
}
