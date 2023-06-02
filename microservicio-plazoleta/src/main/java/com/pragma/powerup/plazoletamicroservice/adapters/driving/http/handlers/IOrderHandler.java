package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.FinishOrderDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrdersCompletedResponseDto;

import java.util.List;
import java.util.Map;

public interface IOrderHandler {
    void createOrder(CreateOrderRequestDto createOrderRequestDto);
    List<OrderResponseDto> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize);
    List<OrdersCompletedResponseDto> getReportOfOrdersCompleted(Long idRestaurant);
    Map<Long, Double> getReportOfOrdersCompletedByEmployee(Long idRestaurant);
    void takeOrder(Long idOrder);
    void markAsReady(Long idOrder);
    void cancelOrder(Long idOrder);
    void finishOrder(FinishOrderDto finishOrderDto);
    String getOrderStatusById(Long idOrder);
}
