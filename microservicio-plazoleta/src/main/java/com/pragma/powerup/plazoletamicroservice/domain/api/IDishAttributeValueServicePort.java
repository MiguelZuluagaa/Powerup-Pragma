package com.pragma.powerup.plazoletamicroservice.domain.api;

import com.pragma.powerup.plazoletamicroservice.domain.model.DishAttributeValue;

public interface IDishAttributeValueServicePort {
    void save(DishAttributeValue dishAttributeValue);
}
