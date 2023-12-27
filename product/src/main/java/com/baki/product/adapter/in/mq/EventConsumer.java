package com.baki.product.adapter.in.mq;

import com.baki.product.application.port.in.DecreaseQuantityUseCase;
import com.baki.product.application.port.in.IncreaseQuantityUseCase;
import com.baki.product.application.service.EventDto;
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
    private final DecreaseQuantityUseCase decreaseQuantityUseCase;
    private final IncreaseQuantityUseCase increaseQuantityUseCase;

    public EventConsumer(ObjectMapper objectMapper, DecreaseQuantityUseCase decreaseQuantityUseCase, IncreaseQuantityUseCase increaseQuantityUseCase) {
        this.objectMapper = objectMapper;
        this.decreaseQuantityUseCase = decreaseQuantityUseCase;
        this.increaseQuantityUseCase = increaseQuantityUseCase;
    }

    @RabbitListener(queues = {"eda.product"})
    public void consumeEvent(String message) {
        try {
            var event = objectMapper.readValue(message, EventDto.class);
            log.info("[consume event] {}", event);

            var orderId = ((Integer) event.payload().get("orderId")).longValue();
            if (event.eventType().equals("PlaceOrder")) {
                decreaseQuantityUseCase.decrease(orderId);
            }

            if (event.eventType().equals("CancelOrder")) {
                increaseQuantityUseCase.increase(orderId);
            }
        } catch (IOException e) {
            log.error("message convert fail");
        }
    }
}
