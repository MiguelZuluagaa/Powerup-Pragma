package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantRequestDto {

    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String name;

    @NotEmpty(message = "El campo nit no puede estar vacio")
    @Digits(integer=13, fraction=0, message="El campo debe contener solo números enteros")
    private String nit;

    @NotEmpty(message = "El campo direccion no puede estar vacia")
    private String direction;

    @NotEmpty(message = "El campo telefono no puede estar vacio")
    @Digits(integer=13, fraction=0, message="El campo telefono debe contener solo números enteros")
    private String phone;

    @NotEmpty(message = "El campo logo no puede estar vacio")
    private String urlLogo;

    @NotNull(message = "El campo idUserOwner no puede estar vacio")
    private Long idUserOwner;
}
