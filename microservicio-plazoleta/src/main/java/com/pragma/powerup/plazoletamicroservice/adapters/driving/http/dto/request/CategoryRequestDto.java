package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryRequestDto {

    @NotEmpty(message = "The name cannot be empty")
    private String name;

    @NotEmpty(message = "The description cannot be empty")
    private String description;
}
