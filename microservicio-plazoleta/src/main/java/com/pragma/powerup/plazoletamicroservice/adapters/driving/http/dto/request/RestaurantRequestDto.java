package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantRequestDto {

    @NotEmpty(message = "The name cannot be empty")
    @Pattern(regexp = "^(?=.*[a-zA-Z]).+$", message = "The name can have letters and numbers, but not only letters")
    private String name;

    @NotEmpty(message = "The nit cannot be empty")
    @Digits(integer=13, fraction=0, message="The nit must be an integer and can contain 13 digits")
    private String nit;

    @NotEmpty(message = "The direction cannot be empty")
    private String direction;

    @NotEmpty(message = "The phone cannot be empty")
    @Digits(integer=13, fraction=0, message="The nit must be an integer and may contain 13 digits and the + symbol")
    private String phone;

    @NotEmpty(message = "The urlLogo cannot be empty")
    private String urlLogo;

    @NotNull(message = "The idUserOwner cannot be empty")
    private Long idUserOwner;
}
