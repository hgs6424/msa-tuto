package com.baki.orchestration.adapter.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class EventDocument {
    @Id
    private String id;
    private String eventPublisher;
    private String eventType;
    private String payload;

    public EventDocument(String id, String eventPublisher, String eventType, String payload) {
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

    public String getPayload() {
        return payload;
    }
}
