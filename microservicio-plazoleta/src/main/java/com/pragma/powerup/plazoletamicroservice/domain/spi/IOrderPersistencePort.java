package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {
    void createOrder(OrderEntity orderEntity);
    Boolean userCanCreateNewOrder(Long idUser);
    void deleteOrderById(Long idOrder);
    List<Order> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize);
}
