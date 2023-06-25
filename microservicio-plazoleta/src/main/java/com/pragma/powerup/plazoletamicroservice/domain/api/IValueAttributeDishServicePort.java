package com.pragma.powerup.plazoletamicroservice.domain.api;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.ValueAttributeDishEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.ValueAttributeDish;

public interface IValueAttributeDishServicePort {
    void save(ValueAttributeDish valueAttributeDish);
    ValueAttributeDishEntity findByValue(String value);
    Boolean existsByValue(String value);
}
