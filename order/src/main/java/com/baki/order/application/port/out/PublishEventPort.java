package com.baki.order.application.port.out;

import com.baki.order.application.EventDto;

public interface PublishEventPort {
    void publish(EventDto eventDto);
}
