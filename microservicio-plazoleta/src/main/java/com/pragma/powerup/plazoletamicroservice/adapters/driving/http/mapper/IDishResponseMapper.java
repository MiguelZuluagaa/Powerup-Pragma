package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.UpdateDishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {
    List<DishResponseDto> toDishResponseList(List<Dish> dishList);
    //Dish toDish(DishRequestDto dishRequestDto);
    Dish toDish(UpdateDishRequestDto updateDishRequestDto);
}
