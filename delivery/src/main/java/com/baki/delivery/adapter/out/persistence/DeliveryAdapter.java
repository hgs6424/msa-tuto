package com.baki.delivery.adapter.out.persistence;

import com.baki.delivery.application.port.out.LoadDeliveryPort;
import com.baki.delivery.application.port.out.SaveDeliveryPort;
import com.baki.delivery.domain.DeliveryDto;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class DeliveryAdapter implements SaveDeliveryPort, LoadDeliveryPort {
    private final DeliveryRepository deliveryRepository;

    public DeliveryAdapter(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }


    @Override
    public DeliveryDto load(Long id) throws NoSuchElementException {
        return deliveryRepository.findById(id).map(DeliveryJpaEntity::toDto).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public DeliveryDto save(DeliveryDto dto) {
        var delivery = DeliveryJpaEntity.fromDto(dto);
        return deliveryRepository.save(delivery).toDto();
    }
}
