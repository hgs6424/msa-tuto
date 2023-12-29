package com.baki.product.application.service;

public record OrderDto(Long id,
                       Long productId,
                       Integer count,
                       Long userId,
                       String status) {
}
