package com.baki.product.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {
}
