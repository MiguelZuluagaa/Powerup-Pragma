package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAuthHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthHandler authHandler;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authHandler.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<JwtResponseDto> refresh(@RequestBody JwtResponseDto jwtResponseDto) throws ParseException {
        return new ResponseEntity<>(authHandler.refresh(jwtResponseDto), HttpStatus.OK);
    }
}
