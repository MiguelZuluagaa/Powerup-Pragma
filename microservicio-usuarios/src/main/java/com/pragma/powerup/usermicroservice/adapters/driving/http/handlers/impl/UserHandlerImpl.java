package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort usuarioServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper usuarioResponseMapper;

    @Override
    public void saveUser(UserRequestDto personRequestDto) {
        usuarioServicePort.saveUser(userRequestMapper.toUser(personRequestDto));
    }

    @Override
    public UserResponseDto findUserByDni(String dni){
        return  usuarioResponseMapper.toResponse(usuarioServicePort.findUserByDni(dni));
    }

    @Override
    public UserResponseDto findUserById(Long id){
        return  usuarioResponseMapper.toResponse(usuarioServicePort.findUserById(id));
    }

    @Override
    public UserResponseDto findUserByEmail(String email){
        return  usuarioResponseMapper.toResponse(usuarioServicePort.findUserByEmail(email));
    }
}
