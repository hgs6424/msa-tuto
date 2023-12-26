package com.baki.order.domain;

public record OrderDto(
        Long id,
        Long prodcutId,
        Integer count,
        Long userId,
        String status
) {
}
