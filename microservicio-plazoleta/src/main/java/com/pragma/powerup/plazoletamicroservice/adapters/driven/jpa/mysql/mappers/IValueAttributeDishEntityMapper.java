package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.ValueAttributeDishEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.ValueAttributeDish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IValueAttributeDishEntityMapper {
    ValueAttributeDishEntity toEntity(ValueAttributeDish valueAttributeDish);
}
