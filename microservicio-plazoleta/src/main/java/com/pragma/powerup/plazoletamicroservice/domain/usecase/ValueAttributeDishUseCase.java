package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.ValueAttributeDishEntity;
import com.pragma.powerup.plazoletamicroservice.domain.api.IValueAttributeDishServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.model.ValueAttributeDish;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IValueAttributeDishPersistencePort;

public class ValueAttributeDishUseCase implements IValueAttributeDishServicePort {
    private final IValueAttributeDishPersistencePort valueAttributeDishPersistencePort;

    public ValueAttributeDishUseCase(IValueAttributeDishPersistencePort valueAttributeDishPersistencePort) {
        this.valueAttributeDishPersistencePort = valueAttributeDishPersistencePort;
    }

    @Override
    public void save(ValueAttributeDish valueAttributeDish){
        if(!existsByValue(valueAttributeDish.getValue())){
            valueAttributeDishPersistencePort.save(valueAttributeDish);
        }else{
            throw new RuntimeException("The value already exists");
        }
    }

    @Override
    public ValueAttributeDishEntity findByValue(String value){
        if(!existsByValue(value)){
            throw new RuntimeException();
        }
        return valueAttributeDishPersistencePort.findByValue(value).get();
    }

    @Override
    public Boolean existsByValue(String value){
        return valueAttributeDishPersistencePort.existsByValue(value);
    }
}
