package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.DishNotFound;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.ParametersNegativesException;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

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
    public List<Dish> getDishesByCategory(Long idCategory, Long pageSize, Long offset) {
        CategoryEntity category = new CategoryEntity(idCategory);

        if(pageSize < 1 || offset < 0 || idCategory < 0){
            throw new ParametersNegativesException();
        }

        Optional<List<DishEntity>> dishesFound = dishRepository.findAllByIdCategory(
                category,
                (PageRequest.of(Math.toIntExact(offset), Math.toIntExact(pageSize))));
        if (dishesFound.get().size() < 1) {
            throw new NoDataFoundException();
        }
        return dishEntityMapper.toDishList(dishesFound.get());
    }

    @Override
    public DishEntity saveDish(Dish dish) {
        return dishRepository.save(dishEntityMapper.toDishEntity(dish));
    }

    @Override
    public void updateDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toDishEntity(dish));
    }

    @Override
    public Optional<Dish> findDishById(Long id) {
        Optional<DishEntity> dishEntity = dishRepository.findById(id);
        if(!dishEntity.isPresent()){
            throw new DishNotFound();
        }
        return Optional.ofNullable(dishEntityMapper.toDish(dishEntity.get()));
    }

    @Override
    public Boolean existDishById(Long id) {
        Optional<DishEntity> dishEntity = dishRepository.findById(id);
        if(!dishEntity.isPresent()){
            return false;
        }
        return true;
    }
}
