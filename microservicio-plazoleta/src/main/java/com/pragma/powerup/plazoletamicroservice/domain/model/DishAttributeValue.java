package com.pragma.powerup.plazoletamicroservice.domain.model;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.AttributeDishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.ValueAttributeDishEntity;

public class DishAttributeValue {

    private Long id;
    private AttributeDishEntity idAttributeDish;
    private ValueAttributeDishEntity idValueAttributeDish;
    private DishEntity idDish;

    public DishAttributeValue(Long id, AttributeDishEntity idAttributeDish, ValueAttributeDishEntity idValueAttributeDish, DishEntity idDish) {
        this.id = id;
        this.idAttributeDish = idAttributeDish;
        this.idValueAttributeDish = idValueAttributeDish;
        this.idDish = idDish;
    }

    public DishAttributeValue(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttributeDishEntity getIdAttributeDish() {
        return idAttributeDish;
    }

    public void setIdAttributeDish(AttributeDishEntity idAttributeDish) {
        this.idAttributeDish = idAttributeDish;
    }

    public ValueAttributeDishEntity getIdValueAttributeDish() {
        return idValueAttributeDish;
    }

    public void setIdValueAttributeDish(ValueAttributeDishEntity idValueAttributeDish) {
        this.idValueAttributeDish = idValueAttributeDish;
    }

    public DishEntity getIdDish() {
        return idDish;
    }

    public void setIdDish(DishEntity idDish) {
        this.idDish = idDish;
    }
}
