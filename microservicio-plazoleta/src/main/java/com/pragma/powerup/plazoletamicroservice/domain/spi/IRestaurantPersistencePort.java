package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IRestaurantPersistencePort {
    List<Restaurant> getAllRestaurants();
    List<Restaurant> getRestaurantsWithPagination(Long pageSize, Long offset);
    void saveRestaurant(Restaurant restaurant);
    Boolean existsByIdUserOwner(Long idUserOwner);
    Optional<Restaurant> findRestaurantById(Long id);
}
