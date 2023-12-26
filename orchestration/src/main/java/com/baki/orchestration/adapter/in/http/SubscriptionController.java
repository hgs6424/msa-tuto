package com.baki.orchestration.adapter.in.http;

import com.baki.orchestration.application.port.in.RegisterSubscriptionUseCase;
import com.baki.orchestration.domain.SubscriptionDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("subscription")
public class SubscriptionController {
    private final RegisterSubscriptionUseCase registerSubscriptionUseCase;

    public SubscriptionController(RegisterSubscriptionUseCase registerSubscriptionUseCase) {
        this.registerSubscriptionUseCase = registerSubscriptionUseCase;
    }

    @PostMapping
    public Mono<Void> register(RegisterSubscriptionRequest request) {
        var dto = new SubscriptionDto(null, request.subscriber(), request.eventPublisher());
        return registerSubscriptionUseCase.register(dto);
    }
}
