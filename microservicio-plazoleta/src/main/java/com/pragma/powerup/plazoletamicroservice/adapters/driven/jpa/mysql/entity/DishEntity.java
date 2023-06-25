package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dish")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @JoinColumn(name = "url_image")
    private String urlImage;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryEntity idCategory;

    @ManyToOne
    @JoinColumn(name ="id_restaurant")
    private RestaurantEntity idRestaurant;

    @ManyToOne
    @JoinColumn(name = "id_type_dish")
    private TypeDishEntity idTypeDish;

    public DishEntity(Long idDish) {
        this.id = idDish;
    }
}
