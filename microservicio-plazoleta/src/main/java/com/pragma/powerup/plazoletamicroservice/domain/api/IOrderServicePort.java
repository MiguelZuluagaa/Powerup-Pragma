package com.pragma.powerup.plazoletamicroservice.domain.api;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.FinishOrderDto;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;

import java.util.List;
import java.util.Map;

public interface IOrderServicePort {
    void createOrder(CreateOrderRequestDto createOrderRequestDto);
    List<Order> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize);
    List<Order> getReportOfOrdersCompleted(Long idRestaurant);
    Map<Long, Double> getReportOfOrdersCompletedByEmployee(Long idRestaurant);
    void takeOrder(Long idOrder);
    void markAsReady(Long idOrder);
    void cancelOrder(Long idOrder);
    void finishOrder(FinishOrderDto finishOrderDto);
    String getOrderStatusById(Long idOrder);
}
