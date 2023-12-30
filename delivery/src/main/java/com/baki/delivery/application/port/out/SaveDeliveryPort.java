package com.baki.delivery.application.port.out;

import com.baki.delivery.domain.DeliveryDto;

public interface SaveDeliveryPort {
    DeliveryDto save(DeliveryDto dto);
}
