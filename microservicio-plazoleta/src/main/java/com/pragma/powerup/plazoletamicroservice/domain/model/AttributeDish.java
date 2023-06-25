package com.pragma.powerup.plazoletamicroservice.domain.model;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.TypeDishEntity;

public class AttributeDish {

    private Long id;
    private String name;


    public AttributeDish(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
