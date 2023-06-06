package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections.TrackingOrderCollection;
import com.pragma.powerup.messengermicroservice.domain.model.TrackingOrder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITrackingOrderEntityMapper {
    TrackingOrderCollection toCollection(TrackingOrder trackingOrder);
    TrackingOrder ofTrackingCollectionToTracking(TrackingOrderCollection trackingOrderCollection);
    List<TrackingOrder> ofTrackingCollectionToTracking(List<TrackingOrderCollection> trackingOrderCollection);
}
