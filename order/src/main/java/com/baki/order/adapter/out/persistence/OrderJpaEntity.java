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

    public OrderJpaEntity(Long id, Long productId, Integer count, Long userId, Status status) {
        this.id = id;
        this.productId = productId;
        this.count = count;
        this.userId = userId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getCount() {
        return count;
    }

    public Long getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }
}
