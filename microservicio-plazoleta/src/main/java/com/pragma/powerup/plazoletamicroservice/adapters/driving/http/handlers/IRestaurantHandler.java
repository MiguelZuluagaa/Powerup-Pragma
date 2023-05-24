package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.RestaurantForCustomersResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRestaurantHandler {
    List<RestaurantResponseDto> getAllRestaurants();
    void saveRestaurant(RestaurantRequestDto restaurant);
    List<RestaurantForCustomersResponseDto> getRestaurantsWithPagination(Long pageSize, Long offset);
}
