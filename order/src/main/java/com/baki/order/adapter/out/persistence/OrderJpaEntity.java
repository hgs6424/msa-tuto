package com.baki.order.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer count;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Status status;

    protected OrderJpaEntity() {
    }

    protected OrderJpaEntity(Long id, Long productId, Integer count, Long userId, Status status) {
        this.id = id;
        this.productId = productId;
        this.count = count;
        this.userId = userId;
        this.status = status;
    }

    Long getId() {
        return id;
    }

    Long getProductId() {
        return productId;
    }

    Integer getCount() {
        return count;
    }

    Long getUserId() {
        return userId;
    }

    Status getStatus() {
        return status;
    }
}
