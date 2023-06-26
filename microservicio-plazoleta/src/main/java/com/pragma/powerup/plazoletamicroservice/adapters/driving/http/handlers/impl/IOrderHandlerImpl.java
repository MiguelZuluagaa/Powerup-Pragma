package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.FinishOrderDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrdersCompletedResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper.IOrderResponseMapper;
import com.pragma.powerup.plazoletamicroservice.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IOrderHandlerImpl implements IOrderHandler {
    private final IOrderResponseMapper orderResponseMapper;
    private final IOrderServicePort orderServicePort;

    @Override
    public void createOrder(CreateOrderRequestDto createOrderRequestDto) {
        orderServicePort.createOrder(createOrderRequestDto);
    }

    @Override
    public List<OrderResponseDto> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize) {
        return orderResponseMapper.toResponseDto(orderServicePort.getOrdersByStatus(idRestaurant,idStatus,offset,pageSize));
    }

    @Override
    public ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> getPendingOrders(Long idRestaurant) {
        return orderServicePort.getPendingOrders(idRestaurant);
    }



    @Override
    public List<OrdersCompletedResponseDto> getReportOfOrdersCompleted(Long idRestaurant) {
        return orderResponseMapper.toOrdersCompletedResponseDto(orderServicePort.getReportOfOrdersCompleted(idRestaurant));
    }

    @Override
    public Map<Long, Double> getReportOfOrdersCompletedByEmployee(Long idRestaurant) {
        return orderServicePort.getReportOfOrdersCompletedByEmployee(idRestaurant);
    }

    @Override
    public void takeOrder(Long idOrder) {
        orderServicePort.takeOrder(idOrder);
    }

    @Override
    public HashMap<OrderEntity, Set<OrderDishEntity>> takeOrderWithPriority(Long idRestaurant) {
        return orderServicePort.takeOrderWithPriority(idRestaurant);
    }

    @Override
    public void markAsReady(Long idOrder) {
        orderServicePort.markAsReady(idOrder);
    }

    @Override
    public void cancelOrder(Long idOrder) {
        orderServicePort.cancelOrder(idOrder);
    }

    @Override
    public void finishOrder(FinishOrderDto finishOrderDto) {
        orderServicePort.finishOrder(finishOrderDto);
    }

    @Override
    public String getOrderStatusById(Long idOrder) {
        return orderServicePort.getOrderStatusById(idOrder);
    }
}
