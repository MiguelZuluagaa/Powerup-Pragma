package com.pragma.powerup.plazoletamicroservice.domain.api;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;

import java.util.HashMap;
import java.util.List;

public interface IDishServicePort {
    List<Dish> getAllDishes();
    List<Dish> getDishesByCategory(Long idCategory, Long pageSize, Long offset);
    void saveDish(Dish dish, HashMap<Long, String> complements);
    void updateDish(Dish dish);
    void activeDish(Long idDish);
    void disableDish(Long idDish);
}
