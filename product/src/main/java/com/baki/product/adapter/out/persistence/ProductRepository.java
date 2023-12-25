package com.baki.product.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {
}
