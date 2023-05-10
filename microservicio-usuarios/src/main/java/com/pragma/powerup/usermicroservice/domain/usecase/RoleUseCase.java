package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.api.IRoleServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Role;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public List<Role> getAllRoles() {
        return rolePersistencePort.getAllRoles();
    }
}
