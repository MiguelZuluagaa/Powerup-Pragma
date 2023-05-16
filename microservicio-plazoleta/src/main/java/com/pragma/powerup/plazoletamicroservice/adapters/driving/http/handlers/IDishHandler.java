package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {
    List<DishResponseDto> getAllDishes();
    void saveDish(DishRequestDto dish);
}
