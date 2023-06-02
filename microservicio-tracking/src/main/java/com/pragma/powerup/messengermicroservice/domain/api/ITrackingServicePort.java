package com.pragma.powerup.messengermicroservice.domain.api;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingOrderDto;
import com.pragma.powerup.messengermicroservice.domain.model.Tracking;

public interface ITrackingServicePort {
    void trackingOrder(Tracking tracking);
}
