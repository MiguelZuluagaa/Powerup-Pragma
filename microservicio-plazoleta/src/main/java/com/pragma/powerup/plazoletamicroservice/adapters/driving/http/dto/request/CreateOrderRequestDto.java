package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request;


import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.assets.DishAsset;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class CreateOrderRequestDto {

    @NotNull(message = "The idRestaurant cannot be empty")
    private Long idRestaurant;

    @NotNull(message = "The dishes cannot be empty")
    ArrayList<DishAsset> dishes;

}
