package com.baki.product.adapter.out.api;

import com.baki.product.application.port.out.LoadOrderPort;
import com.baki.product.application.service.OrderDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderApiAdapter implements LoadOrderPort {
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    public OrderApiAdapter() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public OrderDto load(Long orderId) {
        var url = "http://localhost:8083/order/";
        var request = new HttpEntity<>(null, headers);

        return restTemplate.exchange(url + orderId, HttpMethod.GET, request, OrderDto.class).getBody();
    }
}
