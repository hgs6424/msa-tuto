package com.baki.delivery.application.service;

import com.baki.delivery.application.dto.EventDto;
import com.baki.delivery.application.port.in.FinishDeliveryUseCase;
import com.baki.delivery.application.port.in.RequestDeliveryUseCase;
import com.baki.delivery.application.port.in.StartDeliveryUseCase;
import com.baki.delivery.application.port.out.LoadDeliveryPort;
import com.baki.delivery.application.port.out.LoadOrderPort;
import com.baki.delivery.application.port.out.PublishEventPort;
import com.baki.delivery.application.port.out.SaveDeliveryPort;
import com.baki.delivery.domain.DeliveryService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryManageService implements RequestDeliveryUseCase, StartDeliveryUseCase, FinishDeliveryUseCase {
    private final DeliveryService deliveryService;
    private final LoadDeliveryPort loadDeliveryPort;
    private final SaveDeliveryPort saveDeliveryPort;
    private final LoadOrderPort loadOrderPort;
    private final PublishEventPort publishEventPort;

    public DeliveryManageService(DeliveryService deliveryService, LoadDeliveryPort loadDeliveryPort,
                                 SaveDeliveryPort saveDeliveryPort, LoadOrderPort loadOrderPort,
                                 PublishEventPort publishEventPort) {
        this.deliveryService = deliveryService;
        this.loadDeliveryPort = loadDeliveryPort;
        this.saveDeliveryPort = saveDeliveryPort;
        this.loadOrderPort = loadOrderPort;
        this.publishEventPort = publishEventPort;
    }

    @Override
    public void finish(Long id) {
        var delivery = loadDeliveryPort.load(id);
        delivery = deliveryService.finish(delivery);
        saveDeliveryPort.save(delivery);
        publishEvent(EventDto.finishDeliveryEvent(id));
    }

    @Override
    public void request(Long orderId) {
        var order = loadOrderPort.load(orderId);
        var delivery = deliveryService.create(order.id(), order.productId(), order.userId(), order.sellerId());
        delivery = saveDeliveryPort.save(delivery);
        publishEvent(EventDto.requestDeliveryEvent(delivery.id()));
    }

    @Override
    public void start(Long id) {
        var delivery = loadDeliveryPort.load(id);
        delivery = deliveryService.start(delivery);
        saveDeliveryPort.save(delivery);
        publishEvent(EventDto.startDeliveryEvent(id));
    }

    private void publishEvent(EventDto eventDto) {
        publishEventPort.publish(eventDto);
    }
}
