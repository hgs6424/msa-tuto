package com.baki.delivery.adapter.in.mq;

import com.baki.delivery.application.dto.EventDto;
import com.baki.delivery.application.port.in.RequestDeliveryUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EventConsumer {
    private final Logger log = LoggerFactory.getLogger(EventConsumer.class);
    private final ObjectMapper objectMapper;
    private final RequestDeliveryUseCase requestDeliveryUseCase;

    public EventConsumer(ObjectMapper objectMapper, RequestDeliveryUseCase requestDeliveryUseCase) {
        this.objectMapper = objectMapper;
        this.requestDeliveryUseCase = requestDeliveryUseCase;
    }

    @RabbitListener(queues = {"eda.delivery"})
    public void consumeEvent(String message) {
        try {
            var event = objectMapper.readValue(message, EventDto.class);
            log.info("[consume event] {}", event);

            var orderId = ((Integer) event.payload().get("orderId")).longValue();
            if (event.eventType().equals("PlaceOrder")) {
                requestDeliveryUseCase.request(orderId);
            }
        } catch (IOException e) {
            log.error("message convert fail");
        }
    }
}
