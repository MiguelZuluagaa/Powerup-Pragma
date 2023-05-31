package com.pragma.powerup.messengermicroservice.domain.spi;

import com.pragma.powerup.messengermicroservice.domain.model.Tracking;

public interface ITrackingPersistencePort {
    void trackingOrder(Tracking tracking);
}
