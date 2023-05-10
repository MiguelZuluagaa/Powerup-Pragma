package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.Role;

import java.util.List;

public interface IRoleServicePort {
    List<Role> getAllRoles();
}
