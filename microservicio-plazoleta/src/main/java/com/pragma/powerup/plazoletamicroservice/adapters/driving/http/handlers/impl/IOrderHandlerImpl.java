package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper.IDishResponseMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper.IOrderResponseMapper;
import com.pragma.powerup.plazoletamicroservice.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<OrderResponseDto> getOrdersByStatus(OrderRequestDto orderRequestDto) {
        return orderResponseMapper.toResponseDto(orderServicePort.getOrdersByStatus(orderRequestDto));
    }
}
