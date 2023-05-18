package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateDishRequestDto {

    @NotNull(message = "The id cannot be empty")
    Long id;

    @NotNull(message = "The price cannot be empty")
    @Positive(message = "The price must be a positive number and greater than zero")
    Double price;

    @NotEmpty(message = "The description cannot be empty")
    String description;
}
