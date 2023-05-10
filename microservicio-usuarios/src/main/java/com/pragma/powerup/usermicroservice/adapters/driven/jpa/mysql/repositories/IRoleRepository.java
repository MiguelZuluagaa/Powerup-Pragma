package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {}
