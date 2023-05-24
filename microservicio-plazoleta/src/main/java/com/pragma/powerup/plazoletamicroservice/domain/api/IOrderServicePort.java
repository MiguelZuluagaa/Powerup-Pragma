package com.pragma.powerup.plazoletamicroservice.domain.api;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;

public interface IOrderServicePort {
    void createOrder(CreateOrderRequestDto createOrderRequestDto);
}
