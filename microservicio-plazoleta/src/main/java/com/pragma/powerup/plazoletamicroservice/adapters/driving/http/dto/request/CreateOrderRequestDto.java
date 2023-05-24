package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class CreateOrderRequestDto {

    @NotNull(message = "The idChef cannot be empty")
    private Long idChef;

    @NotNull(message = "The idRestaurant cannot be empty")
    private RestaurantEntity idRestaurant;

    @NotNull(message = "The dishes cannot be empty")
    ArrayList<OrderDishEntity> dishes;
}
