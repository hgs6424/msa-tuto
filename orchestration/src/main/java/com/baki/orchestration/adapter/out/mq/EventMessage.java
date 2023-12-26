package com.baki.orchestration.adapter.out.mq;

import com.baki.orchestration.domain.EventDto;

record EventMessage(
        String exchange,
        String routingKey,
        EventDto payload
) {
}
