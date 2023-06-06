package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections.TrackingOrderCollection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITrackingOrderRepository extends MongoRepository<TrackingOrderCollection, ObjectId> {
}
