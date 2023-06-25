package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity, Long> {
    void deleteByIdOrder(OrderEntity idOrder);
    Optional<Set<OrderDishEntity>> getAllByIdOrder(OrderEntity idOrder);
}
