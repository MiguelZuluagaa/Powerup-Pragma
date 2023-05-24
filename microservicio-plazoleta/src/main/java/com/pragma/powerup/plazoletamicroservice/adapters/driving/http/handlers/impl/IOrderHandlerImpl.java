package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.IOrderHandler;
import com.pragma.powerup.plazoletamicroservice.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IOrderHandlerImpl implements IOrderHandler {
    //private final IDishResponseMapper dishResponseMapper;
    private final IOrderServicePort orderServicePort;

    @Override
    public void createOrder(CreateOrderRequestDto createOrderRequestDto) {
        orderServicePort.createOrder(createOrderRequestDto);
    }
}
