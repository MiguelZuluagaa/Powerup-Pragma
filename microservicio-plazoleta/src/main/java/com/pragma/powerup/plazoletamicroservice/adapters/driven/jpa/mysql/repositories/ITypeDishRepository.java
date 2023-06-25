package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.TypeDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeDishRepository extends JpaRepository<TypeDishEntity, Long> {
}
