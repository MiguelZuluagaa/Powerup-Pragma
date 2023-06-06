package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user);
    void deleteUserById(Long id);
    User findUserByDni(String dni);
    User findUserById(Long id);
    User findUserByEmail(String email);
}
