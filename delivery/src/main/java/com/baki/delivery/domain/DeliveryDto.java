package com.baki.delivery.domain;

import java.time.LocalDateTime;
import java.util.List;

public record DeliveryDto(
        Long id,
        Long orderId,
        String type,
        Long productId,
        Long receiverId,
        Long senderId,
        String status,
        List<DeliveryStatusHistoryDto> deliveryStatusHistories
) {
    public record DeliveryStatusHistoryDto(
            Long id,
            String status,
            LocalDateTime at
    ) {
    }
}
