package com.baki.orchestration.adapter.out.persistence.subscrition;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscriptions")
class SubscriptionDocument {
    @Id
    private String id;
    private String subscriber;
    private String eventPublisher;

    public SubscriptionDocument(String id, String subscriber, String eventPublisher) {
        this.id = id;
        this.subscriber = subscriber;
        this.eventPublisher = eventPublisher;
    }

    public String getId() {
        return id;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public String getEventPublisher() {
        return eventPublisher;
    }
}
