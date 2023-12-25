package com.baki.product.application.port.in;

import com.baki.product.domain.product.ProductDto;

public interface GetProductUseCase extends UseCase  {
    ProductDto get(Long id);
}
