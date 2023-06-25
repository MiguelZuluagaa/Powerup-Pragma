package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "attribute_dish")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttributeDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public AttributeDishEntity(Long id) {
        this.id = id;
    }
}
