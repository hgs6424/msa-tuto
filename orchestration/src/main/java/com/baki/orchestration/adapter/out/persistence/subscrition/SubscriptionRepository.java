package com.baki.orchestration.adapter.out.persistence.subscrition;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
interface SubscriptionRepository extends ReactiveMongoRepository<SubscriptionDocument, String> {
    Flux<SubscriptionDocument> findByEventPublisher(String eventPublisher);
}
