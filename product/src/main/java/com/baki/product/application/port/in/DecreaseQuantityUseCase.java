package com.baki.product.application.port.in;

public interface DecreaseQuantityUseCase extends UseCase  {
    void decrease(Long orderId);
}
