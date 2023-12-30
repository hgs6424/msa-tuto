package com.baki.delivery.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryJpaEntity, Long> {
}
