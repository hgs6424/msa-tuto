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

    public static Order create(Long productId, Integer count, Long userId) {
        return new Order(null, productId, count, userId, Status.REQUESTED.name());
    }

    public static Order fromDto(OrderDto orderDto) {
        return new Order(orderDto.id(), orderDto.prodcutId(), orderDto.count(), orderDto.userId(), orderDto.status());
    }

    public Id getId() {
        return id;
    }

    public Id getProdcutId() {
        return prodcutId;
    }

    public Count getCount() {
        return count;
    }

    public Id getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public void cancel() {
        this.status = Status.CANCELED;
    }
}
