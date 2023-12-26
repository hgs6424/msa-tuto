package com.baki.order.application.port.in;

public interface PlaceOrderUseCase {
    void place(Long productId, Integer count, Long userId);
}
