package com.baki.product.application.port.in;

public interface IncreaseQuantityUseCase extends UseCase  {
    void increase(Long orderId);
}
