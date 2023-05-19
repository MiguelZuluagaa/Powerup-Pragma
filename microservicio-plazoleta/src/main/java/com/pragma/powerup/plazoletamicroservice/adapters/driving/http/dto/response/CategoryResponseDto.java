package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
}
