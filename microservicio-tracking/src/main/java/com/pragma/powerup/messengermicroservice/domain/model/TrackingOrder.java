package com.pragma.powerup.messengermicroservice.domain.model;

import org.bson.types.ObjectId;

import java.util.Date;

public class TrackingOrder {

    private ObjectId id;
    private Long idOrder;
    private Long idCustomer;
    private Long idEmployee;
    private Long idRestaurant;
    private Date date;
    private String previousStatus;
    private String currentStatus;

    public TrackingOrder(ObjectId id, Long idOrder, Long idCustomer, Long idEmployee, Long idRestaurant, Date date, String previousStatus, String currentStatus) {
        this.id = id;
        this.idOrder = idOrder;
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.idRestaurant = idRestaurant;
        this.date = date;
        this.previousStatus = previousStatus;
        this.currentStatus = currentStatus;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
