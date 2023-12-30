package com.baki.delivery.adapter.out.persistence;

import com.baki.delivery.domain.DeliveryDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "deliveries")
public class DeliveryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Long productId;
    private Long receiverId;
    private Long senderId;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<DeliveryStatusHistoryJpaEntity> histories;

    DeliveryJpaEntity(Long id, Long orderId, Type type, Long productId, Long receiverId, Long senderId,
                             Status status, List<DeliveryStatusHistoryJpaEntity> histories) {
        this.id = id;
        this.orderId = orderId;
        this.type = type;
        this.productId = productId;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.status = status;
        this.histories = histories;
    }

    protected DeliveryJpaEntity() {

    }

    DeliveryDto toDto() {
        return new DeliveryDto(this.id, this.orderId, this.type.name(), this.productId, this.receiverId, this.senderId,
                this.status.name(), this.histories.stream().map(DeliveryStatusHistoryJpaEntity::toDto).toList());
    }

    static DeliveryJpaEntity fromDto(DeliveryDto dto) {
        var histories = dto.deliveryStatusHistories().stream().map(DeliveryStatusHistoryJpaEntity::fromDto).toList();

        return new DeliveryJpaEntity(dto.id(), dto.orderId(), Type.valueOf(dto.type()), dto.productId(), dto.receiverId(),
                dto.senderId(), Status.valueOf(dto.status()), histories);
    }
}
