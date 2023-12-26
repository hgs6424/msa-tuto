package com.baki.orchestration.adapter.out.persistence.event;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EventRepository extends ReactiveMongoRepository<EventDocument, String> {
}
