package com.baki.product.application.port.in;

public interface ChangeNameUseCase extends UseCase {
    void change(Long id, String newName);
}
