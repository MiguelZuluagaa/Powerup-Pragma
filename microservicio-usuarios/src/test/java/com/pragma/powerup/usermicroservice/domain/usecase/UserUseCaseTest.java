package com.pragma.powerup.usermicroservice.domain.usecase;


import com.netflix.discovery.converters.Auto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAuthHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl.AuthHandlerImpl;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @Autowired
    private AuthHandlerImpl authHandler;

    private User user;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L,"Juan","Perez","123","123456","12-12-2001","email@gamil.com","123",null);

        //LoginRequestDto loginRequestDto = new LoginRequestDto("admin@gmail.com","999");
        //authHandler.login(loginRequestDto);
    }

    @Test
    void saveUser() {
        userUseCase.saveUser(user);
        verify(userPersistencePort, times(1)).saveUser(user);
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



}