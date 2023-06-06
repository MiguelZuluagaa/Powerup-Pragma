package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDni(String idUser);
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByDni(String dniNumber);
    boolean existsByEmail(String mail);
    List<UserEntity> findAllById(Long id);
    void deleteById(Long id);
}
