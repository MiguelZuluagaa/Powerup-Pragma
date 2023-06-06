package com.pragma.powerup.messengermicroservice.domain.spi;

import com.pragma.powerup.messengermicroservice.domain.model.TrackingOrder;

public interface ITrackingOrderPersistencePort {
    void trackingOrder(TrackingOrder trackingOrder);
}
