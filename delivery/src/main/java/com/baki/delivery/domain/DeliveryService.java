package com.baki.delivery.domain;

import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    public DeliveryDto create(Long orderId, Long productId, Long receiverId, Long senderId) {
        return Delivery.createDelivery(orderId, productId, receiverId, senderId).toDto();
    }

    public DeliveryDto finish(DeliveryDto deliveryDto) {
        var delivery = Delivery.fromDto(deliveryDto);
        delivery.start();
        return delivery.toDto();
    }

    public DeliveryDto start(DeliveryDto deliveryDto) {
        var delivery = Delivery.fromDto(deliveryDto);
        delivery.finish();
        return delivery.toDto();
    }
}
