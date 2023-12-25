package com.baki.product.application.port.out;

import com.baki.product.domain.product.ProductDto;

import java.util.Optional;

public interface LoadPort {
    Optional<ProductDto> load(Long id);
}
