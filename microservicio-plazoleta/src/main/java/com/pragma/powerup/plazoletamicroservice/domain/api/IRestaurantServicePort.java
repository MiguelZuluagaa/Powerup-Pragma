package com.pragma.powerup.plazoletamicroservice.domain.api;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRestaurantServicePort {
    List<Restaurant> getAllRestaurants();
    void saveRestaurant(Restaurant restaurant);
    List<Restaurant> getRestaurantsWithPagination(Long pageSize, Long offset);
}
