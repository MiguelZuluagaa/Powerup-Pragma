package com.pragma.powerup.plazoletamicroservice.domain.model;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;

public class Dish {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String urlImage;
    private Boolean active;
    private CategoryEntity idCategory;
    private RestaurantEntity idRestaurant;

    public Dish(Long id, String name, String description, Double price, String urlImage, Boolean active, CategoryEntity idCategory, RestaurantEntity idRestaurant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.active = active;
        this.idCategory = idCategory;
        this.idRestaurant = idRestaurant;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CategoryEntity getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(CategoryEntity idCategory) {
        this.idCategory = idCategory;
    }

    public RestaurantEntity getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(RestaurantEntity idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
