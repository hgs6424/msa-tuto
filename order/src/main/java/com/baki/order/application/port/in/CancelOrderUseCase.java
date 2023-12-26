package com.baki.order.application.port.in;

public interface CancelOrderUseCase {
    void cancel(Long orderId);
}
