package com.pragma.powerup.messengermicroservice.domain.api;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingRestaurantDto;
import com.pragma.powerup.messengermicroservice.domain.model.TrackingRestaurant;

public interface ITrackingRestaurantServicePort {
    void trackingRestaurant(TrackingRestaurant trackingRestaurant);
}
