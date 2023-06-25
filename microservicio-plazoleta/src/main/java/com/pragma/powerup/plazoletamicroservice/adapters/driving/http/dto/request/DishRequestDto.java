package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request;


import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public class DishRequestDto {

    @NotEmpty(message = "The name cannot be empty")
    private String name;

    @NotEmpty(message = "The description cannot be empty")
    private String description;

    @NotNull(message = "The price cannot be empty")
    @Positive(message = "The price must be a positive number and greater than zero")
    private Double price;

    @NotEmpty(message = "The urlImage cannot be empty")
    private String urlImage;

    @NotNull(message = "The idCategory cannot be empty")
    private Long idCategory;

    @NotNull(message = "The idRestaurant cannot be empty")
    private Long idRestaurant;

    @NotNull(message = "The idTypeDish cannot be empty")
    private Long idTypeDish;

    private HashMap<Long, String> complements;
}
