package com.baki.orchestration.adapter.out.persistence.subscrition;

import com.baki.orchestration.application.port.out.LoadSubscriptionByPublisherPort;
import com.baki.orchestration.application.port.out.SaveSubscriptionPort;
import com.baki.orchestration.domain.SubscriptionDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SubscriptionAdapter implements SaveSubscriptionPort, LoadSubscriptionByPublisherPort {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionAdapter(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Mono<SubscriptionDto> save(SubscriptionDto subscriptionDto) {
        var subscription = new SubscriptionDocument(subscriptionDto.id(), subscriptionDto.subscriber(), subscriptionDto.eventPublisher());
        return subscriptionRepository.save(subscription).map(document -> new SubscriptionDto(
                document.getId(), document.getSubscriber(), document.getEventPublisher()
        ));
    }

    @Override
    public Flux<SubscriptionDto> load(String domainServiceType) {
        return subscriptionRepository.findByEventPublisher(domainServiceType)
                .map(document -> new SubscriptionDto(document.getId(), document.getSubscriber(), document.getEventPublisher()));
    }
}
