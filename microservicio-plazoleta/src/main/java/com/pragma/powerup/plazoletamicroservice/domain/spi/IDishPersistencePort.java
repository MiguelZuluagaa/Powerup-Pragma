package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;


import java.util.List;
import java.util.Optional;

public interface IDishPersistencePort {
    List<Dish> getAllDishes();
    List<Dish> getDishesByCategory(Long idCategory, Long pageSize, Long offset);
    void saveDish(Dish dish);
    void updateDish(Dish dish);
    Optional<Dish> findDishById(Long id);
    Boolean existDishById(Long id);
}
