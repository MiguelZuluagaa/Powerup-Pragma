package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;

import java.util.ArrayList;

public interface IOrderDishPersistencePort {
    //void createOrderDish(ArrayList<OrderDishEntity> orderDishEntities);
    void saveOrderDishes(ArrayList<OrderDishEntity> orderDishEntities);
    void deleteOrderByOrderId(Long idOrder);
}
