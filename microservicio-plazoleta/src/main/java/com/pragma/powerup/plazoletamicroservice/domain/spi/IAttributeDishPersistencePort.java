package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.domain.model.AttributeDish;

public interface IAttributeDishPersistencePort {
    void save(AttributeDish attributeDish);
}
