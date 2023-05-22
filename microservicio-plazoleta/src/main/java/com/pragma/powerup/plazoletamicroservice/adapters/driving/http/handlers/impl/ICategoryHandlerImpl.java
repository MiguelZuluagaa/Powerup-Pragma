package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CategoryRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.ICategoryHandler;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma.powerup.plazoletamicroservice.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ICategoryHandlerImpl implements ICategoryHandler {

    private final ICategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories());
    }

    @Override
    public void saveCategory(CategoryRequestDto category) {
        categoryServicePort.saveCategory(categoryResponseMapper.toCategory(category));
    }
}
