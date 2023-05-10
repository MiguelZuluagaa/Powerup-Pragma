package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;

import java.text.ParseException;

public interface IAuthHandler {
    JwtResponseDto login(LoginRequestDto loginRequestDto);
    JwtResponseDto refresh(JwtResponseDto jwtResponseDto) throws ParseException;

}
