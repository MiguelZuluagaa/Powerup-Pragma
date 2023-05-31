package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.adapter;

import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingPersistencePort;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class TrackingMongoAdapter implements ITrackingPersistencePort {

    @Override
    public void trackingOrder() {
    }
}
