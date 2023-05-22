package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);
    User findUserByDni(String dni);
    User findUserById(Long id);
    User findUserByEmail(String email);
}
