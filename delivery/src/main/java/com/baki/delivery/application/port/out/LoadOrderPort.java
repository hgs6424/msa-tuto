package com.baki.delivery.application.port.out;

import com.baki.delivery.application.dto.OrderDto;

public interface LoadOrderPort {
    OrderDto load(Long id);
}
