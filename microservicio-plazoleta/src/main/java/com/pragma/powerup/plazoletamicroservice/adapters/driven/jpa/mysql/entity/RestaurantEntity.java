package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false, length = 20)
    private String nit;
    private String direction;
    private String phone;
    private String urlLogo;
    @JoinColumn(name = "id_user_owner")
    private Long idUserOwner;
    @JoinColumn(name = "max_processing_time_order")
    private Long maxProcessingTimeOrder;
    @JoinColumn(name = "id_restaurant_status")
    @ManyToOne
    private RestaurantStatusEntity idRestaurantStatus;

    public RestaurantEntity(Long idRestaurant) {
        this.id = idRestaurant;
    }
}
