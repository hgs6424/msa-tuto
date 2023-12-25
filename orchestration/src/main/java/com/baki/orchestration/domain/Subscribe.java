package com.baki.orchestration.domain;

record Subscribe(
        Id id,
        Subscriber subscriber,
        EventPublisher eventPublisher
) {
    public static Subscribe create(String id, String subscriber, String eventPublisher) {
        return new Subscribe(new Id(id), Subscriber.valueOf(subscriber), EventPublisher.valueOf(eventPublisher));
    }
}
