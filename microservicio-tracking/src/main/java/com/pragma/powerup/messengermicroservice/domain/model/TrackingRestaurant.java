package com.pragma.powerup.messengermicroservice.domain.model;

import org.bson.types.ObjectId;

import java.util.Date;

public class TrackingRestaurant {

    private ObjectId id;
    private Long idRestaurant;
    private Date date;
    private String previousStatus;
    private String currentStatus;

    public TrackingRestaurant(ObjectId id, Long idRestaurant, Date date, String previousStatus, String currentStatus) {
        this.id = id;
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
