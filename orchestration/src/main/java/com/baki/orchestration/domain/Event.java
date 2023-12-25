package com.baki.orchestration.domain;

record Event(
        Id id,
        EventPublisher eventPublisher,
        EventType eventType,
        Payload payload)
{
    public static Event create(String id, String eventPublisher, String eventType, String payload) {
        return new Event(new Id(id), EventPublisher.valueOf(eventPublisher), new EventType(eventType), new Payload(payload));
    }
}
