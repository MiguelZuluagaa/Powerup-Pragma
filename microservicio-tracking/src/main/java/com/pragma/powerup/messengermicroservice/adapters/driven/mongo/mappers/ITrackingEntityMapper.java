package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections.TrackingCollection;
import com.pragma.powerup.messengermicroservice.domain.model.Tracking;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITrackingEntityMapper {
    TrackingCollection toCollection(Tracking tracking);
    Tracking ofTrackingCollectionToTracking(TrackingCollection trackingCollection);
    List<Tracking> ofTrackingCollectionToTracking(List<TrackingCollection> trackingCollection);
}
