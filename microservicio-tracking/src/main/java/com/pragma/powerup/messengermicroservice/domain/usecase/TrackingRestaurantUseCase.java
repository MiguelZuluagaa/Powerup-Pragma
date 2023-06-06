package com.pragma.powerup.messengermicroservice.domain.usecase;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingRestaurantDto;
import com.pragma.powerup.messengermicroservice.domain.api.ITrackingRestaurantServicePort;
import com.pragma.powerup.messengermicroservice.domain.model.TrackingRestaurant;
import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingRestaurantPersistencePort;

public class TrackingRestaurantUseCase implements ITrackingRestaurantServicePort {

    private final ITrackingRestaurantPersistencePort trackingRestaurantPersistencePort;

    public TrackingRestaurantUseCase(ITrackingRestaurantPersistencePort trackingRestaurantPersistencePort) {
        this.trackingRestaurantPersistencePort = trackingRestaurantPersistencePort;
    }

    @Override
    public void trackingRestaurant(TrackingRestaurant trackingRestaurant) {
        trackingRestaurantPersistencePort.trackingRestaurant(trackingRestaurant);
    }
}
