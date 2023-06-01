package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderStatusEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findFirstByIdUserAndIdStatus(Long idUser, OrderStatusEntity status);
    Optional<List<OrderEntity>> findAllByIdRestaurantAndIdStatus(RestaurantEntity restaurant, OrderStatusEntity status, PageRequest pageRequest);
    Optional<List<OrderEntity>> findAllByIdRestaurantAndIdStatus(RestaurantEntity idRestaurant, OrderStatusEntity status);
}
