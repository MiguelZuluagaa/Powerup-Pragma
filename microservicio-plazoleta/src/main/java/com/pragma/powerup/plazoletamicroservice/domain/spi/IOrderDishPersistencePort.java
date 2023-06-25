package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public interface IOrderDishPersistencePort {
    //void createOrderDish(ArrayList<OrderDishEntity> orderDishEntities);
    void saveOrderDishes(ArrayList<OrderDishEntity> orderDishEntities);
    void deleteOrderByOrderId(Long idOrder);
    Optional<Set<OrderDishEntity>> getOrderDishesByIdOrder(Long idOrder);
}
