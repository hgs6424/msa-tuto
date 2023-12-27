package com.baki.product.application.port.out;

import com.baki.product.application.service.OrderDto;

public interface LoadOrderPort {
    OrderDto load(Long orderId);
}
