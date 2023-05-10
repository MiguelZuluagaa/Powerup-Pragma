package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    void saveUser(User usuario);
    User findUserByDni(String numeroDocumento);
    User findUserById(Long id);
}
