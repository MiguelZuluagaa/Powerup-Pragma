package com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.dto;

import com.pragma.powerup.plazoletamicroservice.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserFeignDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String dni;
    private String birdDate;
    private String password;
    private Role idRole;
}
