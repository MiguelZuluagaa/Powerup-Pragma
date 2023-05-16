package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DishMysqlAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public List<Dish> getAllDishes() {
        List<DishEntity> dishEntityList = dishRepository.findAll();
        if (dishEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return dishEntityMapper.toDishList(dishEntityList);
    }

    @Override
    public void saveDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toDish(dish));
    }
}
