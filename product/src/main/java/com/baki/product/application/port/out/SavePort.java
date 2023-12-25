package com.baki.product.application.port.out;

import com.baki.product.domain.product.ProductDto;

public interface SavePort {
    ProductDto save(ProductDto productDto);
}
