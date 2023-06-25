package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.ValueAttributeDishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IValueAttributeDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IValueAttributeDishRepository;
import com.pragma.powerup.plazoletamicroservice.domain.model.ValueAttributeDish;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IValueAttributeDishPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ValueAttributeDishMysqlAdapter implements IValueAttributeDishPersistencePort {
    private final IValueAttributeDishRepository valueAttributeDishRepository;
    private final IValueAttributeDishEntityMapper valueAttributeDishEntityMapper;

    @Override
    public void save(ValueAttributeDish valueAttributeDish) {
        valueAttributeDishRepository.save(valueAttributeDishEntityMapper.toEntity(valueAttributeDish));
    }

    @Override
    public Optional<ValueAttributeDishEntity> findByValue(String value) {
        return valueAttributeDishRepository.findByValue(value);
    }

    @Override
    public Boolean existsByValue(String value) {
        return valueAttributeDishRepository.existsByValue(value);
    }
}
