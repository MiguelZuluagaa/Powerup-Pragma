package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;



public interface IUsuarioHandler {
    void saveUser(UserRequestDto usuarioRequestDto);
    UserResponseDto findUserByDni(String numeroDocumento);
    UserResponseDto findUserById(Long id);
}
