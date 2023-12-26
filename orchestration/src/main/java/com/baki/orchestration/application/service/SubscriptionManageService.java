package com.baki.orchestration.application.service;

import com.baki.orchestration.application.port.in.RegisterSubscriptionUseCase;
import com.baki.orchestration.application.port.out.SaveSubscriptionPort;
import com.baki.orchestration.domain.SubscriptionDto;
import com.baki.orchestration.domain.SubscriptionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SubscriptionManageService implements RegisterSubscriptionUseCase {
    private final SaveSubscriptionPort saveSubscriptionPort;
    private final SubscriptionService subscriptionService;

    public SubscriptionManageService(SaveSubscriptionPort saveSubscriptionPort, SubscriptionService subscriptionService) {
        this.saveSubscriptionPort = saveSubscriptionPort;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public Mono<Void> register(SubscriptionDto dto) {
        var subscription = subscriptionService.create(dto);
        return saveSubscriptionPort.save(subscription).then();
    }
}
