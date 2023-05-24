package com.pragma.powerup.plazoletamicroservice.domain.model;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import jakarta.persistence.*;

public class OrderDish {

    private Long id;
    private OrderEntity idOrder;
    private DishEntity idDish;
    private Long quantity;

    public OrderDish(Long id, OrderEntity idOrder, DishEntity idDish, Long quantity) {
        this.id = id;
        this.idOrder = idOrder;
        this.idDish = idDish;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(OrderEntity idOrder) {
        this.idOrder = idOrder;
    }

    public DishEntity getIdDish() {
        return idDish;
    }

    public void setIdDish(DishEntity idDish) {
        this.idDish = idDish;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
