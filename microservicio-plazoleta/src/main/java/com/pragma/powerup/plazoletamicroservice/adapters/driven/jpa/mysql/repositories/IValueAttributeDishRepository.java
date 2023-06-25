package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.ValueAttributeDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IValueAttributeDishRepository extends JpaRepository<ValueAttributeDishEntity, Long> {
    Boolean existsByValue(String value);
    Optional<ValueAttributeDishEntity> findByValue(String value);
}
