package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders_dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity idOrder;

    @ManyToOne
    @JoinColumn(name = "id_dish")
    private DishEntity idDish;

    @ManyToOne
    @JoinColumn(name = "id_dish_attribute_value")
    private DishAttributeValueEntity idDishAttributeValue;

    private Long quantity;
}
