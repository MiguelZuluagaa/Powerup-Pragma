package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishAttributeValueEntity;
import com.pragma.powerup.plazoletamicroservice.domain.api.IDishAttributeValueServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.ComplementNotFoundException;
import com.pragma.powerup.plazoletamicroservice.domain.model.DishAttributeValue;
import com.pragma.powerup.plazoletamicroservice.domain.model.ValueAttributeDish;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishAttributeValuePersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DishAttributeValueUseCase implements IDishAttributeValueServicePort {
    private final IDishAttributeValuePersistencePort dishAttributeValuePersistencePort;

    @Autowired
    private ValueAttributeDishUseCase valueAttributeDishEntityUseCase;

    public DishAttributeValueUseCase(IDishAttributeValuePersistencePort dishAttributeValuePersistencePort) {
        this.dishAttributeValuePersistencePort = dishAttributeValuePersistencePort;
    }

    @Override
    public void save(DishAttributeValue dishAttributeValue){
        if(!valueAttributeDishEntityUseCase.existsByValue(dishAttributeValue.getIdValueAttributeDish().getValue())){
            valueAttributeDishEntityUseCase.save(new ValueAttributeDish(dishAttributeValue.getIdValueAttributeDish().getValue()));
        }
        dishAttributeValue.setIdValueAttributeDish(valueAttributeDishEntityUseCase.findByValue(dishAttributeValue.getIdValueAttributeDish().getValue()));
        dishAttributeValuePersistencePort.save(dishAttributeValue);
    }

    public DishAttributeValueEntity findById(Long id){
        Optional<DishAttributeValueEntity> recordFound = dishAttributeValuePersistencePort.findById(id);
        if(!recordFound.isPresent()){
            throw new ComplementNotFoundException();
        }
        return recordFound.get();
    }

}
