package com.baki.orchestration.adapter.in.http;

import com.baki.orchestration.application.port.in.OccurEventUseCase;
import com.baki.orchestration.domain.EventDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("event")
public class EventController {
    private final OccurEventUseCase occurEventUseCase;

    public EventController(OccurEventUseCase occurEventUseCase) {
        this.occurEventUseCase = occurEventUseCase;
    }

    @PostMapping
    public Mono<Void> occurEvent(@RequestBody OccurEventRequest request) {
        return occurEventUseCase.occur(new EventDto(null, request.eventPublisher(), request.eventType(), request.payload()));
    }
}
