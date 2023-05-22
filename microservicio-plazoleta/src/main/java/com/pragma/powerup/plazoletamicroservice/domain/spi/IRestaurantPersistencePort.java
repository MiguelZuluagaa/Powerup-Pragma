package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantPersistencePort {
    List<Restaurant> getAllRestaurants();
    void saveRestaurant(Restaurant restaurant);
    Boolean existsByIdUserOwner(Long idUserOwner);
    Optional<Restaurant> findRestaurantById(Long id);
}
