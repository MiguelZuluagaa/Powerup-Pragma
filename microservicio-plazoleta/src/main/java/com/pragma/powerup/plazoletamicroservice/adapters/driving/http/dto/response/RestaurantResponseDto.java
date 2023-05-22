package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String nit;
    private String direction;
    private String phone;
    private String urlLogo;
    private Long idUserOwner;
}
