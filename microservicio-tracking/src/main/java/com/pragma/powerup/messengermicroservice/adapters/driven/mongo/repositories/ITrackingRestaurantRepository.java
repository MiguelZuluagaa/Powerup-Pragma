package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections.TrackingRestaurantCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITrackingRestaurantRepository extends MongoRepository<TrackingRestaurantCollection, Long> {
}
