package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    List<Restaurant> getAllRestaurants();
    void saveRestaurant(Restaurant restaurant);
}
