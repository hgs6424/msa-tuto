package com.baki.orchestration.adapter.out.persistence.subscrition;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SubscriptionRepository extends ReactiveMongoRepository<SubscriptionDocument, String> {
}
