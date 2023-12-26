package com.baki.orchestration.adapter.in.http;

import com.baki.orchestration.application.port.in.RegisterEventUseCase;
import com.baki.orchestration.domain.EventDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("event")
public class EventController {
    private final RegisterEventUseCase registerEventUseCase;

    public EventController(RegisterEventUseCase registerEventUseCase) {
        this.registerEventUseCase = registerEventUseCase;
    }

    @PostMapping
    public Mono<Void> registerEvent(@RequestBody RegisterEventRequest request) {
        return registerEventUseCase.register(new EventDto(null, request.eventPublisher(), request.eventType(), request.payload()));
    }
}
