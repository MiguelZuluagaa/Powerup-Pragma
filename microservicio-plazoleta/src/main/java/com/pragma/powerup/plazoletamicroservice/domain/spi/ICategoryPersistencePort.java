package com.pragma.powerup.plazoletamicroservice.domain.spi;

import com.pragma.powerup.plazoletamicroservice.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    List<Category> getAllCategories();
    void saveCategory(Category category);
    Category getCategoryById(Long id);
    Boolean existCategoryById(Long id);
}
