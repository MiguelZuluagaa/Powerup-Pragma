package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class LoginRequestDto {
    @NotBlank(message = "email is required")
    @Email(message = "email is not valid")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
}
