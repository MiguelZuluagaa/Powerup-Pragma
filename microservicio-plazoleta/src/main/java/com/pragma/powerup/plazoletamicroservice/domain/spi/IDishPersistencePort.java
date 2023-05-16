package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;

import java.util.List;

public interface IDishPersistencePort {
    List<Dish> getAllDishes();
    void saveDish(Dish dish);
}
