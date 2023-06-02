package com.pragma.powerup.messengermicroservice.domain.spi;

import com.pragma.powerup.messengermicroservice.domain.model.Tracking;

import java.util.List;

public interface ITrackingPersistencePort {
    void trackingOrder(Tracking tracking);
}
