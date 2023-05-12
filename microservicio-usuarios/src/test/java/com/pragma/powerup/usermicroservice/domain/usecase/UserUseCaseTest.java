package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        role = new Role(1L, "ROLE_OWNER","ROLE_OWNER");
        user = new User(1L,"Juan","Perez","123","123456","12-12-2001","email@gamil.com","123",role);
    }

    @Test
    void findUserById() {
        when(userPersistencePort.findUserById(1L)).thenReturn(user);
        assertNotNull(userUseCase.findUserById(1L));
    }

    @Test
    void findUserByDni() {
        when(userPersistencePort.findUserByDni("123")).thenReturn(user);
        assertNotNull(userUseCase.findUserByDni("123"));
    }

    @Test
    void saveUser() {
        userUseCase.saveUser(user);
        verify(userPersistencePort, times(1)).saveUser(user);
    }

}