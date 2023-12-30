package com.baki.delivery.adapter.out.mq;

import com.baki.delivery.application.dto.EventDto;
import com.baki.delivery.application.port.out.PublishEventPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventAdapter implements PublishEventPort {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    public static final String EXCHANGE = "eda";
    public static final String ROUTING_KEY = "event";
    private final Logger log = LoggerFactory.getLogger(EventAdapter.class);

    public EventAdapter(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(EventDto eventDto) {
        try {
            var message = objectMapper.writeValueAsString(eventDto);
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
            log.info("[publish message] exchange = {}, routingKey = {}, message = {}", EXCHANGE, ROUTING_KEY, message);
        } catch (JsonProcessingException e) {
            log.error("message parsing error : {}", e.getMessage());
        }
    }
}
