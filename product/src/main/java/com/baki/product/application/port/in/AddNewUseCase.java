package com.baki.product.application.port.in;

public interface AddNewUseCase extends UseCase {
    void add(String name, Integer quantity);
}
