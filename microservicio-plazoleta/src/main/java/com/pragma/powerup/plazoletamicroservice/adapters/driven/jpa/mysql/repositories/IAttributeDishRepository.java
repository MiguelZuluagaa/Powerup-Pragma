package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.AttributeDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttributeDishRepository extends JpaRepository<AttributeDishEntity, Long> {
}
