package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findFirstByIdUserAndIdStatus(Long idUser, OrderStatusEntity status);
}
