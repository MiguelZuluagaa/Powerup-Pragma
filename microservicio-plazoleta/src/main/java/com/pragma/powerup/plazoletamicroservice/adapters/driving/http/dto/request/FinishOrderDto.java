package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FinishOrderDto {

    @NotNull(message = "The idOrder cannot be empty")
    @Positive(message = "The idOrder must be positive")
    private Long idOrder;

    @NotNull(message = "The pin of the order cannot be empty")
    @Pattern(regexp = "^([a-zA-Z0-9]){4}$", message = "The pin of the order must be 4 digits")
    private String pinOrder;
}
