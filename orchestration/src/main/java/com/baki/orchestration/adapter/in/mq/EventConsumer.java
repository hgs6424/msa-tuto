package com.baki.orchestration.adapter.in.mq;

import com.baki.orchestration.application.port.in.RegisterEventUseCase;
import com.baki.orchestration.domain.EventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
public class EventConsumer {
    private final Logger log = LoggerFactory.getLogger(EventConsumer.class);
    private final ObjectMapper objectMapper;
    private final RegisterEventUseCase registerEventUseCase;

    public EventConsumer(RegisterEventUseCase registerEventUseCase, ObjectMapper objectMapper) {
        this.registerEventUseCase = registerEventUseCase;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = {"eda.event"}, ackMode = "MANUAL")
    public Mono<Void> consumeEvent(String message) {
        try {
            var event = objectMapper.readValue(message, EventDto.class);
            log.info("[consume event] {}", event);
            return registerEventUseCase.register(event).then();
        } catch (IOException e) {
            log.error("message convert fail");
        }
        return Mono.empty();
    }
}
