package com.baki.order.application.port.out;

import com.baki.order.common.ProductDto;

public interface LoadProductPort {
    ProductDto load(Long productId);
}
