package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;

public interface IOrderHandler {
    void createOrder(CreateOrderRequestDto createOrderRequestDto);
}
