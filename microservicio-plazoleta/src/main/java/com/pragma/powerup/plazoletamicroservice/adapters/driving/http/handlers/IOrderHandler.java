package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.FinishOrderDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrdersCompletedResponseDto;

import java.util.*;

public interface IOrderHandler {
    void createOrder(CreateOrderRequestDto createOrderRequestDto);
    List<OrderResponseDto> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize);
    ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> getPendingOrders(Long idRestaurant);
    List<OrdersCompletedResponseDto> getReportOfOrdersCompleted(Long idRestaurant);
    Map<Long, Double> getReportOfOrdersCompletedByEmployee(Long idRestaurant);
    void takeOrder(Long idOrder);
    HashMap<OrderEntity, Set<OrderDishEntity>> takeOrderWithPriority(Long idRestaurant);
    void markAsReady(Long idOrder);
    void cancelOrder(Long idOrder);
    void finishOrder(FinishOrderDto finishOrderDto);
    String getOrderStatusById(Long idOrder);
}
