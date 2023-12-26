package com.baki.order.application.port.out;

import com.baki.order.domain.OrderDto;

import java.util.Optional;

public interface LoadOrderPort {
    Optional<OrderDto> load(Long orderId);
}
