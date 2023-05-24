package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderStatusEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderRequestDto {

    @NotNull(message = "The idRestaurant cannot be empty")
    RestaurantEntity idRestaurant;

    @NotNull(message = "The idStatus cannot be empty")
    OrderStatusEntity idStatus;
}
