package com.baki.delivery.adapter.out.persistence;

import com.baki.delivery.domain.DeliveryDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_status_histories")
public class DeliveryStatusHistoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryJpaEntity delivery;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime at;

    public DeliveryStatusHistoryJpaEntity(Long id, Status status, LocalDateTime at) {
        this.id = id;
        this.status = status;
        this.at = at;
    }

    DeliveryDto.DeliveryStatusHistoryDto toDto() {
        return new DeliveryDto.DeliveryStatusHistoryDto(this.id, this.status.name(), this.at);
    }

    static DeliveryStatusHistoryJpaEntity fromDto(DeliveryDto.DeliveryStatusHistoryDto dto) {
        return new DeliveryStatusHistoryJpaEntity(dto.id(), Status.valueOf(dto.status()), dto.at());
    }
}
