package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections.TrackingRestaurantCollection;
import com.pragma.powerup.messengermicroservice.domain.model.TrackingRestaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITrackingRestaurantEntityMapper {
    TrackingRestaurantCollection toCollection(TrackingRestaurant trackingRestaurant);
}
