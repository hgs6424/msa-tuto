package com.baki.order.application.port.out;

import com.baki.order.domain.OrderDto;

public interface SaveOrderPort {
    OrderDto save(OrderDto orderDto);
}
