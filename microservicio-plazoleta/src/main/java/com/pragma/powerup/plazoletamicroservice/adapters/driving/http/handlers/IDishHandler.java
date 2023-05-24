package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.UpdateDishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.DishResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IDishHandler {
    List<DishResponseDto> getAllDishes();
    List<DishResponseDto> getDishesByCategory(Long idCategory, Long pageSize, Long offset);
    void saveDish(DishRequestDto dish);
    void updateDish(UpdateDishRequestDto dish);
    void activeDish(Long idDish);
    void disableDish(Long idDish);
}
