package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishAttributeValueEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.DishAttributeValue;

import java.util.Optional;

public interface IDishAttributeValuePersistencePort {
    void save(DishAttributeValue dishAttributeValue);
    Optional<DishAttributeValueEntity> findById(Long id);
}
