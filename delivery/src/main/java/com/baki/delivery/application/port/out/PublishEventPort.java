package com.baki.delivery.application.port.out;

import com.baki.delivery.application.dto.EventDto;

public interface PublishEventPort {
    void publish(EventDto eventDto);
}
