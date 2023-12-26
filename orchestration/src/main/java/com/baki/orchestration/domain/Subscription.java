package com.baki.orchestration.domain;

record Subscription(
        Id id,
        DomainServiceType subscriber,
        DomainServiceType eventPublisher
) {
    public static Subscription create(String id, String subscriber, String eventPublisher) {
        return new Subscription(new Id(id), DomainServiceType.valueOf(subscriber), DomainServiceType.valueOf(eventPublisher));
    }
}
