package com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingRestaurantDto;
import com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.ITrackingRestaurantHandler;
import com.pragma.powerup.messengermicroservice.adapters.driving.http.mapper.ITrackingRestaurantResponseMapper;
import com.pragma.powerup.messengermicroservice.domain.api.ITrackingRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ITrackingRestaurantHandlerImpl implements ITrackingRestaurantHandler {

    private final ITrackingRestaurantResponseMapper trackingRestaurantResponseMapper;
    private final ITrackingRestaurantServicePort trackingRestaurantServicePort;


    @Override
    public void trackingRestaurant(CreateTrackingRestaurantDto createTrackingRestaurantDto) {
        trackingRestaurantServicePort.trackingRestaurant(trackingRestaurantResponseMapper.toTrackingRestaurant(createTrackingRestaurantDto));
    }
}
