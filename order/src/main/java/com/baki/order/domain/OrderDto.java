package com.baki.order.domain;

public record OrderDto(
        Long id,
        Long productId,
        Integer count,
        Long userId,
        String status
) {
}
