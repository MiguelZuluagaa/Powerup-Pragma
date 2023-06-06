package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.RestaurantForCustomersResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.plazoletamicroservice.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto){
        restaurantServicePort.saveRestaurant(restaurantResponseMapper.toRestaurant(restaurantRequestDto));
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantServicePort.deleteRestaurant(id);
    }

    @Override
    public List<RestaurantForCustomersResponseDto> getRestaurantsWithPagination(Long pageSize, Long offset){
        return restaurantResponseMapper.toResponseListForCustomers(restaurantServicePort.getRestaurantsWithPagination(pageSize, offset));
    }

}
