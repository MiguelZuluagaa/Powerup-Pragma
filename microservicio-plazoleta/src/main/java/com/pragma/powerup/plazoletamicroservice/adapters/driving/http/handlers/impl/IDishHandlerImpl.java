package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.UpdateDishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.IDishHandler;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper.IDishResponseMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.plazoletamicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IDishHandlerImpl implements IDishHandler {
    private final IDishResponseMapper dishResponseMapper;
    private final IDishServicePort dishServicePort;

    @Override
    public List<DishResponseDto> getAllDishes() {
        return dishResponseMapper.toDishResponseList(dishServicePort.getAllDishes());
    }

    @Override
    public void saveDish(DishRequestDto dish) {
        dishServicePort.saveDish(dishResponseMapper.toDish(dish));
    }

    @Override
    public void updateDish(UpdateDishRequestDto dish) {
        dishServicePort.updateDish(dishResponseMapper.toDish(dish));
    }

    @Override
    public void activeDish(Long idDish) {
        dishServicePort.activeDish(idDish);
    }

    @Override
    public void disableDish(Long idDish) {
        dishServicePort.disableDish(idDish);
    }
}
