package com.baki.product.domain.product;

public record ProductDto(
        Long id,
        String name,
        Integer quantity,
        String status
) {
    public static ProductDto fromDomain(Product product) {
        return new ProductDto(product.getId().value(),
                product.getName().value(),
                product.getQuantity().value(),
                product.getStatus().name());
    }

    Product toDomain() {
        return new Product(this.id, this.name, this.quantity, this.status);
    }
}
