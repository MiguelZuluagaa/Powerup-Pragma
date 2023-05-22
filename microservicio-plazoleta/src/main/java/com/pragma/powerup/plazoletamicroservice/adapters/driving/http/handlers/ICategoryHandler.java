package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CategoryRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {
    List<CategoryResponseDto> getAllCategories();
    void saveCategory(CategoryRequestDto category);
}
