package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.domain.api.IAttributeDishServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.model.AttributeDish;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IAttributeDishPersistencePort;

public class AttributeDishUseCase implements IAttributeDishServicePort {
    private final IAttributeDishPersistencePort attributeDishPersistencePort;

    public AttributeDishUseCase(IAttributeDishPersistencePort attributeDishPersistencePort) {
        this.attributeDishPersistencePort = attributeDishPersistencePort;
    }

    @Override
    public void save(AttributeDish attributeDish){
        attributeDishPersistencePort.save(attributeDish);
    }
}
