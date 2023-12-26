package com.baki.order.adapter.in.http;

public record PlaceOrderRequest(
        Long productId,
        Integer count,
        Long userId
) {
}
