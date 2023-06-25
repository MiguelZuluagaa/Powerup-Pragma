package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dish_attribute_value")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishAttributeValueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_attribute_dish")
    private AttributeDishEntity idAttributeDish;

    @ManyToOne
    @JoinColumn(name = "id_value_dish")
    private ValueAttributeDishEntity idValueAttributeDish;

    @ManyToOne
    @JoinColumn(name = "id_dish")
    private DishEntity idDish;

}
