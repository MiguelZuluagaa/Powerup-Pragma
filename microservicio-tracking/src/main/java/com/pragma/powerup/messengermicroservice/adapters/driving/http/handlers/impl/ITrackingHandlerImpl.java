package com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingOrderDto;
import com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.ITrackingHandler;
import com.pragma.powerup.messengermicroservice.adapters.driving.http.mapper.ITrackingResponseMapper;
import com.pragma.powerup.messengermicroservice.domain.api.ITrackingServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ITrackingHandlerImpl implements ITrackingHandler {

    private final ITrackingResponseMapper trackingResponseMapper;
    private final ITrackingServicePort messengerServicePort;

    @Override
    public void trackingOrder(CreateTrackingOrderDto createTrackingOrderDto) {
        messengerServicePort.trackingOrder(trackingResponseMapper.toTracking(createTrackingOrderDto));
    }
}
