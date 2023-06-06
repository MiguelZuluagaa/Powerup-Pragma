package com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTrackingRestaurantDto {
    @NotNull(message = "The idEmployee cannot be empty")
    private Long idRestaurant;

    @NotEmpty(message = "The previousStatus cannot be empty")
    private String previousStatus;

    @NotEmpty(message = "The currentStatus cannot be empty")
    private String currentStatus;

}
