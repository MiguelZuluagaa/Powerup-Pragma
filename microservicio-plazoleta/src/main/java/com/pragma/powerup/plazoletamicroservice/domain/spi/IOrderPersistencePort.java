package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderPersistencePort {
    void createOrder(OrderEntity orderEntity);
    Boolean userCanCreateNewOrder(Long idUser);
    void deleteOrderById(Long idOrder);
    List<Order> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize);
    void takeOrder(Long idOrder, Long idUser);
    Optional<OrderEntity> findOrderById(Long idOrder);
}
