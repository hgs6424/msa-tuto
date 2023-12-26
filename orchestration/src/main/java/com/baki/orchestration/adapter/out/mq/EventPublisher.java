package com.baki.orchestration.adapter.out.mq;

import com.baki.orchestration.application.port.out.PublishEventPort;
import com.baki.orchestration.domain.EventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EventPublisher implements PublishEventPort {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    public static final String EXCHANGE = "eda";

    public EventPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> publish(Mono<EventDto> dtoMono, Flux<String> subscribersFlux) {
        return dtoMono.flatMapMany(dto ->{
                    try {
                        var message = objectMapper.writeValueAsString(dto);
                        return subscribersFlux.flatMap(subscriber ->
                                Mono.fromRunnable(() ->
                                        rabbitTemplate.convertAndSend(EXCHANGE, subscriber.toLowerCase(), message)));
                    } catch (JsonProcessingException e) {
                        return Mono.error(RuntimeException::new);
                    }
                }).then();
    }
}
