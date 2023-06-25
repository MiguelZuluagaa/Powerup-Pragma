package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;


import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IAttributeDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IAttributeDishRepository;
import com.pragma.powerup.plazoletamicroservice.domain.model.AttributeDish;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IAttributeDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttributeDishMysqlAdapter implements IAttributeDishPersistencePort {
    private final IAttributeDishRepository attributeDishRepository;
    private final IAttributeDishEntityMapper attributeDishEntityMapper;


    @Override
    public void save(AttributeDish attributeDish) {
        attributeDishRepository.save(attributeDishEntityMapper.toEntity(attributeDish));
    }
}
