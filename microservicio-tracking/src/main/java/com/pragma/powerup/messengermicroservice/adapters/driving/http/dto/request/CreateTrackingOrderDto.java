package com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CreateTrackingOrderDto {

    @NotNull(message = "The idOrder cannot be empty")
    private Long idOrder;

    @NotNull(message = "The idCustomer cannot be empty")
    private Long idCustomer;


    private Long idEmployee;

    @NotNull(message = "The idEmployee cannot be empty")
    private Long idRestaurant;


    private String previousStatus;

    @NotEmpty(message = "The currentStatus cannot be empty")
    private String currentStatus;
}
