package com.baki.orchestration.domain;

import org.springframework.stereotype.Service;

@Service
public class EventService {

    public EventDto create(EventDto eventDto) {
        var event = Event.create(eventDto.id(), eventDto.eventPublisher(), eventDto.eventType(), eventDto.payload());
        return new EventDto(event.id().value(), event.eventPublisher().name(), event.eventType().value(), event.payload().value());
    }
}
