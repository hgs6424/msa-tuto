package com.baki.delivery.domain;

import java.time.LocalDateTime;
import java.util.List;

class Delivery {
    private Id id;
    private Id orderId;
    private Type type;
    private Id productId;
    private Id receiverId;
    private Id senderId;
    private Status status;
    private List<DeliveryStatusHistory> deliveryStatusHistories;

    Delivery(Long id, Long orderId, String type, Long productId, Long receiverId, Long senderId, String status,
             List<DeliveryStatusHistory> deliveryStatusHistories) {
        this.id = new Id(id);
        this.orderId = new Id(orderId);
        this.type = Type.valueOf(type);
        this.productId = new Id(productId);
        this.receiverId = new Id(receiverId);
        this.senderId = new Id(senderId);
        this.status = Status.valueOf(status);
        this.deliveryStatusHistories = deliveryStatusHistories;
    }

    static Delivery createDelivery(Long orderId, Long productId, Long receiverId, Long senderId) {
        return new Delivery(null, orderId, Type.DELIVERY.name(), productId, receiverId, senderId, Status.REQUESTED.name(),
                List.of(new DeliveryStatusHistory(null, Status.REQUESTED.name(), LocalDateTime.now())));
    }

    DeliveryDto toDto() {
        var deliveryStatusHistory = this.deliveryStatusHistories.stream()
                .map(it -> new DeliveryDto.DeliveryStatusHistoryDto(it.getId().value(), it.getStatus().name(), it.getAt()))
                .toList();

        return new DeliveryDto(this.id.value(), this.orderId.value(), this.type.name(), this.productId.value(),
                this.receiverId.value(), this.senderId.value(), this.status.name(), deliveryStatusHistory);
    }

    static Delivery fromDto(DeliveryDto dto) {
        var histories = dto.deliveryStatusHistories().stream()
                .map(it -> new DeliveryStatusHistory(it.id(), it.status(), it.at()))
                .toList();

        return new Delivery(dto.id(), dto.orderId(), dto.type(), dto.productId(), dto.receiverId(), dto.senderId(),
                dto.status(), histories);
    }

    public Id getId() {
        return id;
    }

    public Id getOrderId() {
        return orderId;
    }

    public Type getType() {
        return type;
    }

    public Id getProductId() {
        return productId;
    }

    public Id getReceiverId() {
        return receiverId;
    }

    public Id getSenderId() {
        return senderId;
    }

    public Status getStatus() {
        return status;
    }

    public List<DeliveryStatusHistory> getDeliveryStatusHistories() {
        return deliveryStatusHistories;
    }

    public void start() {
        this.status = Status.SHIPPING;
    }

    public void finish() {
        this.status = Status.SHIPPED;
    }
}
