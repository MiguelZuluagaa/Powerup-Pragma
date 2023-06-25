package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;


import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.ITypeDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.ITypeDishRepository;
import com.pragma.powerup.plazoletamicroservice.domain.spi.ITypeDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TypeDishMysqlAdapter implements ITypeDishPersistencePort {
    private final ITypeDishRepository typeDishRepository;
    private final ITypeDishEntityMapper typeDishEntityMapper;
}
