package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.ValueAttributeDishEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.ValueAttributeDish;

import java.util.Optional;

public interface IValueAttributeDishPersistencePort {
    void save(ValueAttributeDish valueAttributeDish);
    Optional<ValueAttributeDishEntity> findByValue(String value);
    Boolean existsByValue(String value);
}
