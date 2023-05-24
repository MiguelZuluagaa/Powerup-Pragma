package com.pragma.powerup.plazoletamicroservice.domain.model;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderStatusEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import jakarta.persistence.*;

import java.util.Date;

public class Order {

    private Long id;
    private Long idUser;
    private Date date;
    private OrderStatusEntity idStatus;
    private Long idChef;
    private RestaurantEntity idRestaurant;

    public Order(Long id, Long idUser, Date date, OrderStatusEntity idStatus, Long idChef, RestaurantEntity idRestaurant) {
        this.id = id;
        this.idUser = idUser;
        this.date = date;
        this.idStatus = idStatus;
        this.idChef = idChef;
        this.idRestaurant = idRestaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderStatusEntity getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(OrderStatusEntity idStatus) {
        this.idStatus = idStatus;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }

    public RestaurantEntity getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(RestaurantEntity idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
