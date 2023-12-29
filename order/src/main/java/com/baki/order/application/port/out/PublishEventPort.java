package com.baki.order.application.port.out;

import com.baki.order.common.EventDto;

public interface PublishEventPort {
    void publish(EventDto eventDto);
}
