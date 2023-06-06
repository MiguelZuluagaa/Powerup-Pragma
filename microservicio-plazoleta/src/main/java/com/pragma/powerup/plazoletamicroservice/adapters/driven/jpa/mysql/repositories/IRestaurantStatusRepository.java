package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantStatusRepository extends JpaRepository<RestaurantStatusEntity, Long> {
}
