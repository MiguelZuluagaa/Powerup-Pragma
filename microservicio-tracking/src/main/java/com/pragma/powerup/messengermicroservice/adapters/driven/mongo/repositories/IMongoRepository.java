package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections.TrackingCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMongoRepository extends MongoRepository<TrackingCollection, Long> {
}
