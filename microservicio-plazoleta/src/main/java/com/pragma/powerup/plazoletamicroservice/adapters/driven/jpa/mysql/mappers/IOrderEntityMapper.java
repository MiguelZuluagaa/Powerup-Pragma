package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
    List<Order> toOrderList(List<OrderEntity> listOrderEntity);
    Set<Order> toOrderList(Set<OrderEntity> listOrderEntity);
}
