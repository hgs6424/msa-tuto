package com.baki.product.application.service;

public record OrderDto(Long id,
                       Long prodcutId,
                       Integer count,
                       Long userId,
                       String status) {
}
