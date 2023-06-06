package com.pragma.powerup.messengermicroservice.domain.api;

import com.pragma.powerup.messengermicroservice.domain.model.TrackingOrder;

public interface ITrackingOrderServicePort {
    void trackingOrder(TrackingOrder trackingOrder);
}
