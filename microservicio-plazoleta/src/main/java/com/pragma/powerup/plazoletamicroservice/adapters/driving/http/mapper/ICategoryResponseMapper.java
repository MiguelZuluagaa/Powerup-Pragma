package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CategoryRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma.powerup.plazoletamicroservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {
    Category toCategory(CategoryRequestDto categoryRequestDto);
    List<CategoryResponseDto> toCategoryResponseList(List<Category> categoryList);

}
