package com.pragma.powerup.plazoletamicroservice.domain.api;

import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    List<Restaurant> getAllRestaurants();
    void saveRestaurant(Restaurant restaurant, String authorization);
}
