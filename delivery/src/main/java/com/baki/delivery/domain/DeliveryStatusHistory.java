package com.baki.delivery.domain;

import java.time.LocalDateTime;

class DeliveryStatusHistory {
    private Id id;
    private Status status;
    private LocalDateTime at;

    DeliveryStatusHistory(Long id, String status, LocalDateTime at) {
        this.id = new Id(id);
        this.status = Status.valueOf(status);
        this.at = at;

    }

    static DeliveryStatusHistory fromDto(DeliveryDto.DeliveryStatusHistoryDto dto) {
        return new DeliveryStatusHistory(dto.id(), dto.status(), dto.at());
    }

    public Id getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getAt() {
        return at;
    }
}
