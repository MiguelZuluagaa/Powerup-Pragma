package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.FinishOrderDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;

import java.util.List;

public interface IOrderHandler {
    void createOrder(CreateOrderRequestDto createOrderRequestDto);
    List<OrderResponseDto> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize);
    void takeOrder(Long idOrder);
    void markAsReady(Long idOrder);
    void cancelOrder(Long idOrder);
    void finishOrder(FinishOrderDto finishOrderDto);
    String getOrderStatusById(Long idOrder);
}
