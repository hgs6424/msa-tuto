package com.baki.orchestration.domain;

import java.util.Map;

record Event(
        Id id,
        DomainServiceType eventPublisher,
        EventType eventType,
        Payload payload)
{
    public static Event create(String id, String eventPublisher, String eventType, Map<String, Object> payload) {
        return new Event(new Id(id), DomainServiceType.valueOf(eventPublisher), new EventType(eventType), new Payload(payload));
    }
}
