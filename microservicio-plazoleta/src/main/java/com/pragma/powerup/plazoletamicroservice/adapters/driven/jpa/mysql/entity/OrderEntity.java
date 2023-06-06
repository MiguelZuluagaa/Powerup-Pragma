package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "order_head")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_customer")
    private Long idUser;

    private Date date;

    @JoinColumn(name ="completion_time_minutes")
    private Double completionTimeMinutes;

    private String pinOrder;

    @ManyToOne
    @JoinColumn(name ="id_status")
    private OrderStatusEntity idStatus;

    @JoinColumn(name = "id_chef")
    private Long idChef;

    @ManyToOne
    @JoinColumn(name ="id_restaurant")
    private RestaurantEntity idRestaurant;

    public OrderEntity(Long idOrder) {
        this.id = idOrder;
    }
}
