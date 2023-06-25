package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishAttributeValueEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IDishAttributeValueEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IDishAttributeValueRepository;
import com.pragma.powerup.plazoletamicroservice.domain.model.DishAttributeValue;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IAttributeDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishAttributeValuePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DishAttributeValueMysqlAdapter implements IDishAttributeValuePersistencePort {
    private final IDishAttributeValueRepository dishAttributeValueRepository;
    private final IDishAttributeValueEntityMapper dishAttributeValueEntityMapper;

    @Override
    public void save(DishAttributeValue dishAttributeValue) {
        dishAttributeValueRepository.save(dishAttributeValueEntityMapper.toEntity(dishAttributeValue));
    }

    @Override
    public Optional<DishAttributeValueEntity> findById(Long id) {
        Optional<DishAttributeValueEntity> record = dishAttributeValueRepository.findById(id);
        return record;
    }
}
