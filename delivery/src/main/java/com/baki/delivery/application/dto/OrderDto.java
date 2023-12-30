package com.baki.delivery.application.dto;

public record OrderDto(
        Long id,
        Long productId,
        Long userId,
        Long sellerId
) {
}
