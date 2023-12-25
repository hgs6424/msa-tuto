package com.baki.product.adapter.out.persistence;

import com.baki.product.application.port.out.LoadPort;
import com.baki.product.application.port.out.SavePort;
import com.baki.product.domain.product.ProductDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductAdapter implements LoadPort, SavePort {
    private final ProductRepository productRepository;

    public ProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<ProductDto> load(Long id) {
        return productRepository.findById(id).map(entity ->
                new ProductDto(entity.getId(), entity.getName(), entity.getQuantity(), entity.getStatus().name()));
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        var entity = productRepository.save(new ProductJpaEntity(productDto.id(), productDto.name(), productDto.quantity(),
                productDto.status()));

        return new ProductDto(entity.getId(), entity.getName(), entity.getQuantity(), entity.getStatus().name());
    }
}
