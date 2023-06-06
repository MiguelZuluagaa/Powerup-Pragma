package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderPersistencePort {
    void createOrder(OrderEntity orderEntity);
    void deleteOrderById(Long idOrder);
    void takeOrder(Long idOrder, Long idUser);
    void markOrderReady(OrderEntity order);
    void cancelOrder(OrderEntity order);
    void markOrderFinished(OrderEntity order);
    void deleteByIdRestaurant(Long idRestaurant);
    Boolean userCanCreateNewOrder(Long idUser);
    Boolean existsOrdersInCurseByIdRestaurant(Long idRestaurant);
    List<Order> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize);
    List<Order> getOrdersByIdRestaurant(Long idRestaurant);
    List<Order> findAllByIdRestaurantAndIdStatus(Long idRestaurant,Long idStatus);
    Optional<OrderEntity> findOrderById(Long idOrder);
    Optional<List<Object>> testMethod();
    Optional<List<Long>> getAllOrdersWithMaxProcessingTime();
}
