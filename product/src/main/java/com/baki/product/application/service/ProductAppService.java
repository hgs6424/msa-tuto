package com.baki.product.application.service;

import com.baki.product.application.port.in.*;
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

    public ProductAppService(ProductService productService, LoadPort loadPort, SavePort savePort) {
        this.productService = productService;
        this.loadPort = loadPort;
        this.savePort = savePort;
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
    public void decrease(Long id, Integer count) {
        var productDto = loadPort.load(id).orElseThrow(NoSuchElementException::new);
        productDto = productService.decrease(productDto, count);
        savePort.save(productDto);
    }

    @Override
    public ProductDto get(Long id) {
        return loadPort.load(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void increase(Long id, Integer count) {
        var productDto = loadPort.load(id).orElseThrow(NoSuchElementException::new);
        productDto = productService.increase(productDto, count);
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
