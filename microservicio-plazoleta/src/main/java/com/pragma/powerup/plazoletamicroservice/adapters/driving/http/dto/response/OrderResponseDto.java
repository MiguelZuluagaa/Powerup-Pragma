package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderStatusEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderResponseDto {
    private Long id;
    private Long idUser;
    private String date;
    private OrderStatusEntity idStatus;
    private Long idChef;
    private RestaurantEntity idRestaurant;
}
