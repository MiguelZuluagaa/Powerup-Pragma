package com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingOrderDto;
import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingRestaurantDto;

public interface ITrackingRestaurantHandler {
    void trackingRestaurant(CreateTrackingRestaurantDto createTrackingRestaurantDto);
}
