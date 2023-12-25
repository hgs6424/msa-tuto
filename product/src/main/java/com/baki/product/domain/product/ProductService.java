package com.baki.product.domain.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductDto create(String name, Integer quantity) {
        return ProductDto.fromDomain(Product.create(name, quantity));
    }

    public ProductDto changeName(ProductDto productDto, String newName) {
        var product = productDto.toDomain();
        product.changeName(newName);
        return ProductDto.fromDomain(product);
    }

    public ProductDto decrease(ProductDto productDto, Integer count) {
        var product = productDto.toDomain();
        product.decrease(count);
        return ProductDto.fromDomain(product);
    }

    public ProductDto increase(ProductDto productDto, Integer count) {
        var product = productDto.toDomain();
        product.increase(count);
        return ProductDto.fromDomain(product);
    }

    public ProductDto stopSelling(ProductDto productDto) {
        var product = productDto.toDomain();
        product.stopSelling();
        return ProductDto.fromDomain(product);
    }

    public ProductDto sellOut(ProductDto productDto) {
        var product = productDto.toDomain();
        product.sellOut();
        return ProductDto.fromDomain(product);
    }
}
