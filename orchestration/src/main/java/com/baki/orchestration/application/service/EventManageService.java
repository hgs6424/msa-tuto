package com.baki.orchestration.application.service;

import com.baki.orchestration.application.port.in.RegisterEventUseCase;
import com.baki.orchestration.application.port.out.LoadSubscriptionByPublisherPort;
import com.baki.orchestration.application.port.out.PublishEventPort;
import com.baki.orchestration.application.port.out.SaveEventPort;
import com.baki.orchestration.domain.EventDto;
import com.baki.orchestration.domain.EventService;
import com.baki.orchestration.domain.SubscriptionDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EventManageService implements RegisterEventUseCase {
    private final EventService eventService;
    private final SaveEventPort saveEventPort;
    private final PublishEventPort publishEventPort;
    private final LoadSubscriptionByPublisherPort loadSubscriptionByPublisherPort;

    public EventManageService(SaveEventPort saveEventPort, EventService eventService, PublishEventPort publishEventPort,
                              LoadSubscriptionByPublisherPort loadSubscriptionByPublisherPort) {
        this.saveEventPort = saveEventPort;
        this.eventService = eventService;
        this.publishEventPort = publishEventPort;
        this.loadSubscriptionByPublisherPort = loadSubscriptionByPublisherPort;
    }

    @Override
    public Mono<Void> register(EventDto eventDto) {
        var event = eventService.create(eventDto);
        var savedEvent = saveEventPort.save(event);
        var subscribers = loadSubscriptionByPublisherPort.load(event.eventPublisher())
                .map(SubscriptionDto::subscriber);
        return publishEventPort.publish(savedEvent, subscribers);
    }
}
