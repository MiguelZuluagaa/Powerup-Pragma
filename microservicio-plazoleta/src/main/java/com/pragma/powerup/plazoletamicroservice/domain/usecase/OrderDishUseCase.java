package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.domain.api.IOrderDishServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderDishPersistencePort;

public class OrderDishUseCase implements IOrderDishServicePort {
    private final IOrderDishPersistencePort orderDishPersistencePort;

    public OrderDishUseCase(IOrderDishPersistencePort orderDishPersistencePort) {
        this.orderDishPersistencePort = orderDishPersistencePort;
    }
}
