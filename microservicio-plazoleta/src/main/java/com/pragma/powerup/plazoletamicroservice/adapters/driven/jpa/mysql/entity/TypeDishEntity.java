package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "type_dish")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TypeDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public TypeDishEntity(Long idTypeDish) {
        this.id = idTypeDish;
    }
}
