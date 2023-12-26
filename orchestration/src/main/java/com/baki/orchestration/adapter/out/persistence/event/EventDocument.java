package com.baki.orchestration.adapter.out.persistence.event;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "events")
class EventDocument {
    @Id
    private String id;
    private String eventPublisher;
    private String eventType;
    private Map<String ,Object> payload;

    public EventDocument(String id, String eventPublisher, String eventType, Map<String ,Object> payload) {
        this.id = id;
        this.eventPublisher = eventPublisher;
        this.eventType = eventType;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public String getEventPublisher() {
        return eventPublisher;
    }

    public String getEventType() {
        return eventType;
    }

    public Map<String ,Object> getPayload() {
        return payload;
    }
}
