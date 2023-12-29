package com.baki.order.application.port.in;

import com.baki.order.common.OutOfStockException;

public interface PlaceOrderUseCase {
    void place(Long productId, Integer count, Long userId) throws OutOfStockException;
}
