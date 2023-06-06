package com.pragma.powerup.messengermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingOrderDto;
import com.pragma.powerup.messengermicroservice.domain.model.TrackingOrder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITrackingResponseMapper {
    TrackingOrder toTrackingOrder(CreateTrackingOrderDto createTrackingOrderDto);
}
