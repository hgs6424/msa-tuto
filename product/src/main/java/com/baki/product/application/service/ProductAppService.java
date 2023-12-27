package com.baki.product.application.service;

import com.baki.product.application.port.in.*;
import com.baki.product.application.port.out.LoadOrderPort;
import com.baki.product.application.port.out.LoadPort;
import com.baki.product.application.port.out.SavePort;
import com.baki.product.domain.product.ProductDto;
import com.baki.product.domain.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductAppService implements
        AddNewUseCase, ChangeNameUseCase, DecreaseQuantityUseCase, IncreaseQuantityUseCase, GetProductUseCase,
SellOutUseCase, StopSellingUseCase {
    private final ProductService productService;
    private final LoadPort loadPort;
    private final SavePort savePort;
    private final LoadOrderPort loadOrderPort;

    public ProductAppService(ProductService productService, LoadPort loadPort, SavePort savePort, LoadOrderPort loadOrderPort) {
        this.productService = productService;
        this.loadPort = loadPort;
        this.savePort = savePort;
        this.loadOrderPort = loadOrderPort;
    }

    @Override
    public void add(String name, Integer quantity) {
        var productDto = productService.create(name, quantity);
        savePort.save(productDto);
    }

    @Override
    public void change(Long id, String newName) {
        var productDto = loadPort.load(id).orElseThrow(NoSuchElementException::new);
        productDto = productService.changeName(productDto, newName);
        savePort.save(productDto);
    }

    @Override
    public void decrease(Long orderId) {
        var orderDto = loadOrderPort.load(orderId);
        var productDto = loadPort.load(orderDto.prodcutId()).orElseThrow(NoSuchElementException::new);
        productDto = productService.decrease(productDto, orderDto.count());
        savePort.save(productDto);
    }

    @Override
    public ProductDto get(Long id) {
        return loadPort.load(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void increase(Long orderId) {
        var orderDto = loadOrderPort.load(orderId);
        var productDto = loadPort.load(orderId).orElseThrow(NoSuchElementException::new);
        productDto = productService.increase(productDto, orderDto.count());
        savePort.save(productDto);
    }

    @Override
    public void sellOut(Long id) {
        var productDto = loadPort.load(id).orElseThrow(NoSuchElementException::new);
        productDto = productService.sellOut(productDto);
        savePort.save(productDto);
    }

    @Override
    public void stop(Long id) {
        var productDto = loadPort.load(id).orElseThrow(NoSuchElementException::new);
        productDto = productService.stopSelling(productDto);
        savePort.save(productDto);
    }
}
