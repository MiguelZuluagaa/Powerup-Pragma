package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.adapter;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers.ITrackingRestaurantEntityMapper;
import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories.ITrackingRestaurantRepository;
import com.pragma.powerup.messengermicroservice.domain.model.TrackingRestaurant;
import com.pragma.powerup.messengermicroservice.domain.spi.ITrackingRestaurantPersistencePort;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class TrackingRestaurantMongoAdapter implements ITrackingRestaurantPersistencePort {

    private final ITrackingRestaurantRepository restaurantRepository;
    private final ITrackingRestaurantEntityMapper trackingRestaurantEntityMapper;

    @Override
    public void trackingRestaurant(TrackingRestaurant trackingRestaurant) {
        trackingRestaurant.setDate(new Date());
        restaurantRepository.save(trackingRestaurantEntityMapper.toCollection(trackingRestaurant));
    }
}
