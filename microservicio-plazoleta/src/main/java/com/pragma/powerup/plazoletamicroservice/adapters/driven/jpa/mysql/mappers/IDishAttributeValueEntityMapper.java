package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishAttributeValueEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.DishAttributeValue;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishAttributeValueEntityMapper {
    DishAttributeValueEntity toEntity(DishAttributeValue dishAttributeValue);
}
