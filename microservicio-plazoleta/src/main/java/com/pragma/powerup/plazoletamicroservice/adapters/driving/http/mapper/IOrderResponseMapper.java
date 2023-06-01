package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrdersCompletedResponseDto;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {
    List<OrderResponseDto> toResponseDto(List<Order> orders);
    List<OrdersCompletedResponseDto> toOrdersCompletedResponseDto(List<Order> orders);
}
