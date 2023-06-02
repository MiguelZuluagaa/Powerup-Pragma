package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.repositories;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections.TrackingCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ITrackingRepository extends MongoRepository<TrackingCollection, Long> {
}
