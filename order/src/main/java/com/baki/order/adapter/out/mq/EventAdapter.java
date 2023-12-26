package com.baki.order.adapter.out.mq;

import com.baki.order.application.EventDto;
import com.baki.order.application.port.out.PublishEventPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventAdapter implements PublishEventPort {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    public static final String EXCHANGE = "eda";
    public static final String ROUTING_KEY = "eda";

    public EventAdapter(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(EventDto eventDto) {
        try {
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, objectMapper.writeValueAsString(eventDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
