package com.baki.delivery.application.port.out;

import com.baki.delivery.domain.DeliveryDto;

public interface LoadDeliveryPort {
    DeliveryDto load(Long id);
}
