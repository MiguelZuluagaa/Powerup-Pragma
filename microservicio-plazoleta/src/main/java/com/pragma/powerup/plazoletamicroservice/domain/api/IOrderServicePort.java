package com.pragma.powerup.plazoletamicroservice.domain.api;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {
    void createOrder(CreateOrderRequestDto createOrderRequestDto);
    List<Order> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize);
    void takeOrder(Long idOrder);
}
