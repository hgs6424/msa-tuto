package com.baki.orchestration.adapter.out.persistence.event;

import com.baki.orchestration.application.port.out.SaveEventPort;
import com.baki.orchestration.domain.EventDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class EventAdapter implements SaveEventPort {
    private final EventRepository eventRepository;

    public EventAdapter(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Mono<EventDto> save(EventDto eventdto) {
        var eventDocument = new EventDocument(eventdto.id(), eventdto.eventPublisher(), eventdto.eventType(), eventdto.payload());
        return eventRepository.save(eventDocument).map(document -> new EventDto(
                document.getId(), document.getEventPublisher(), document.getEventType(), document.getPayload()
        ));
    }
}
