package com.pragma.powerup.plazoletamicroservice.domain.api;

import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;

import java.util.List;

public interface IDishServicePort {
    List<Dish> getAllDishes();
    void saveDish(Dish dish);
}
