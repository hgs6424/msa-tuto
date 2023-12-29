package com.baki.order.domain;

class Order {
    private Id id;
    private Id prodcutId;
    private Count count;
    private Id userId;
    private Status status;

    private Order(Long id, Long productId, Integer count, Long userId, String status) {
        this.id = new Id(id);
        this.prodcutId = new Id(productId);
        this.count = new Count(count);
        this.userId = new Id(userId);
        this.status = Status.valueOf(status);
    }

    static Order create(Long productId, Integer count, Long userId) {
        return new Order(null, productId, count, userId, Status.COMPLETED.name());
    }

    static Order fromDto(OrderDto orderDto) {
        return new Order(orderDto.id(), orderDto.productId(), orderDto.count(), orderDto.userId(), orderDto.status());
    }

    Id getId() {
        return id;
    }

    Id getProdcutId() {
        return prodcutId;
    }

    Count getCount() {
        return count;
    }

    Id getUserId() {
        return userId;
    }

    Status getStatus() {
        return status;
    }

    void cancel() {
        this.status = Status.CANCELED;
    }

    void reject() {
        this.status = Status.REJECTED;
    }
}
