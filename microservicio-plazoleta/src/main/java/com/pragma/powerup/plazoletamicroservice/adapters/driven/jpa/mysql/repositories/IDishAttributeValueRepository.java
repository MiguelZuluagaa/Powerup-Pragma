package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishAttributeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishAttributeValueRepository extends JpaRepository<DishAttributeValueEntity, Long> {
    Optional<DishAttributeValueEntity> findById(Long id);
}
