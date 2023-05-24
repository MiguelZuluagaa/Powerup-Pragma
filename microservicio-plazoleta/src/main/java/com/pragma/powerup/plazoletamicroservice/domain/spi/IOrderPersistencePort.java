package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;

public interface IOrderPersistencePort {
    void createOrder(OrderEntity orderEntity);
    Boolean userCanCreateNewOrder(Long idUser);
    void deleteOrderById(Long idOrder);
}
