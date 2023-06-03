package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.CategoryDontExistException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.plazoletamicroservice.domain.model.Category;
import com.pragma.powerup.plazoletamicroservice.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

    @RequiredArgsConstructor
public class CategoryMysqlAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if(!categoryEntity.isPresent()){
            throw new CategoryDontExistException();
        }
        return categoryEntityMapper.toCategory(categoryEntity.get());
    }

    @Override
    public Boolean existCategoryById(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if(!categoryEntity.isPresent()){
            throw new CategoryDontExistException();
        }
        return true;
    }
}
