package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.Role;

import java.util.List;

public interface IRolePersistencePort {
    List<Role> getAllRoles();
}
