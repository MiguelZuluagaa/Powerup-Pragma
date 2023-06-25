package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.TypeDishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.UpdateDishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.IDishHandler;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper.IDishResponseMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.plazoletamicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public List<DishResponseDto> getDishesByCategory(Long idCategory, Long pageSize, Long offset) {
        return dishResponseMapper.toDishResponseList(dishServicePort.getDishesByCategory(idCategory, pageSize, offset));
    }

    @Override
    public void saveDish(DishRequestDto dish) {
        HashMap<Long, String> complements = null;
        if(dish.getComplements() != null) complements = dish.getComplements();

        Dish dishModel = new Dish();
        dishModel.setName(dish.getName());
        dishModel.setDescription(dish.getDescription());
        dishModel.setPrice(dish.getPrice());
        dishModel.setIdCategory(new CategoryEntity(dish.getIdCategory()));
        dishModel.setIdRestaurant(new RestaurantEntity(dish.getIdRestaurant()));
        dishModel.setIdTypeDish(new TypeDishEntity(dish.getIdTypeDish()));
        dishServicePort.saveDish(dishModel, complements);
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
