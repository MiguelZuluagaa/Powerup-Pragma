package com.pragma.powerup.messengermicroservice.domain.usecase;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingOrderDto;
import com.pragma.powerup.messengermicroservice.domain.api.ITrackingServicePort;
import com.pragma.powerup.messengermicroservice.domain.model.Tracking;
import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingPersistencePort;

public class TrackingUseCase implements ITrackingServicePort {
    private final ITrackingPersistencePort messengerPersistencePort;

    public TrackingUseCase(ITrackingPersistencePort messengerPersistencePort) {
        this.messengerPersistencePort = messengerPersistencePort;
    }

    @Override
    public void trackingOrder(Tracking tracking) {
        messengerPersistencePort.trackingOrder(tracking);
    }
}
