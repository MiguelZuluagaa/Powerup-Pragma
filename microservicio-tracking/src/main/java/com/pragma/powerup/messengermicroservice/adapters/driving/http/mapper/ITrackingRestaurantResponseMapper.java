package com.pragma.powerup.messengermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingRestaurantDto;
import com.pragma.powerup.messengermicroservice.domain.model.TrackingRestaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITrackingRestaurantResponseMapper {
    TrackingRestaurant toTrackingRestaurant(CreateTrackingRestaurantDto createTrackingRestaurantDto);
}
