package com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingOrderDto;

public interface ITrackingHandler {
    void trackingOrder(CreateTrackingOrderDto createTrackingOrderDto);
}
