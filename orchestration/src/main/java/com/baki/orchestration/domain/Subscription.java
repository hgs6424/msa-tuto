package com.baki.orchestration.domain;

record Subscription(
        Id id,
        Subscriber subscriber,
        EventPublisher eventPublisher
) {
    public static Subscription create(String id, String subscriber, String eventPublisher) {
        return new Subscription(new Id(id), Subscriber.valueOf(subscriber), EventPublisher.valueOf(eventPublisher));
    }
}
