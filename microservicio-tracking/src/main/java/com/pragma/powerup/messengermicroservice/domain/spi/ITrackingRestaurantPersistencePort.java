package com.pragma.powerup.messengermicroservice.domain.spi;

import com.pragma.powerup.messengermicroservice.domain.model.TrackingRestaurant;

public interface ITrackingRestaurantPersistencePort {
    void trackingRestaurant(TrackingRestaurant trackingRestaurant);
}
