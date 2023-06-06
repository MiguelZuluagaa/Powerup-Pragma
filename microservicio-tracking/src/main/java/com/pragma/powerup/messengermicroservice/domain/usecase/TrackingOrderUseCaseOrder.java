package com.pragma.powerup.messengermicroservice.domain.usecase;

import com.pragma.powerup.messengermicroservice.domain.api.ITrackingOrderServicePort;
import com.pragma.powerup.messengermicroservice.domain.model.TrackingOrder;
import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingOrderPersistencePort;

public class TrackingOrderUseCaseOrder implements ITrackingOrderServicePort {
    private final ITrackingOrderPersistencePort messengerPersistencePort;

    public TrackingOrderUseCaseOrder(ITrackingOrderPersistencePort messengerPersistencePort) {
        this.messengerPersistencePort = messengerPersistencePort;
    }

    @Override
    public void trackingOrder(TrackingOrder trackingOrder) {
        messengerPersistencePort.trackingOrder(trackingOrder);
    }
}
