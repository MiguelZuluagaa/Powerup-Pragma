package com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingOrderDto;

public interface ITrackingOrderHandler {
    void trackingOrder(CreateTrackingOrderDto createTrackingOrderDto);
}
