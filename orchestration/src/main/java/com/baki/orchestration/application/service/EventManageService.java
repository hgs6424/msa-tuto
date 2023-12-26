package com.baki.orchestration.application.service;

import com.baki.orchestration.application.port.in.OccurEventUseCase;
import com.baki.orchestration.application.port.out.SaveEventPort;
import com.baki.orchestration.domain.EventDto;
import com.baki.orchestration.domain.EventService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EventManageService implements OccurEventUseCase {
    private final SaveEventPort saveEventPort;
    private final EventService eventService;

    public EventManageService(SaveEventPort saveEventPort, EventService eventService) {
        this.saveEventPort = saveEventPort;
        this.eventService = eventService;
    }

    @Override
    public Mono<Void> occur(EventDto eventDto) {
        var event = eventService.create(eventDto);
        return saveEventPort.save(event).then();
    }
}
