package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishResponseDto {
    private String name;
    private String description;
    private Double price;
    private String urlImage;
    private Boolean active;
    private CategoryEntity idCategory;
    private RestaurantEntity idRestaurant;
}
