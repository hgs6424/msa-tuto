package com.baki.product.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "products", schema = "PRODUCT")
class ProductJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer quantity;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    protected ProductJpaEntity() {
    }

    public ProductJpaEntity(Long id, String name, Integer quantity, String status) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.status = Status.valueOf(status);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Status getStatus() {
        return status;
    }
}
